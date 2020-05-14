package com.smc.sp.service.impl;

import com.smc.sp.dto.*;
import com.smc.sp.entity.StockPrice;
import com.smc.sp.feign.FeignClients;
import com.smc.sp.repository.StockPriceRepository;
import com.smc.sp.service.IPageService;
import com.smc.sp.service.IStockPriceService;
import com.smc.sp.vo.CompanyCodeStockExchangeName;
import com.smc.sp.vo.StockPriceChartVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockPriceServiceImpl implements IStockPriceService {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final SimpleDateFormat simpleShortDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private IPageService pageService;

    @Autowired
    private StockPriceRepository stockPriceRepository;

    @Autowired
    private FeignClients.CompanyClient companyClient;

    @Override
    public PageDto<StockPriceDto> findAll(Pageable pageable) {
        Page<StockPrice> page = stockPriceRepository.findAll(pageable);
        return pageService.convertToPageDto(page, this::convertToDto);
    }

    @Override
    public Summary importStockPrices(MultipartFile file) {
        Summary summary = new Summary();
        StringBuilder buffer = new StringBuilder("uploadImportExcel start...\n");
        Workbook workbook = null;
        try {
            String fileName = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();

            if (fileName.matches("^.+\\.(?i)(xls)$")) { // EXCEL2003
                workbook = new HSSFWorkbook(inputStream);
            }
            if (fileName.matches("^.+\\.(?i)(xlsx)$")) { // EXCEL2007
                workbook = new XSSFWorkbook(inputStream);
            }

            if (workbook != null) {
                Sheet sheet = workbook.getSheetAt(0);
                //lastRowNum starts from 0, so totalRows should be lastRowNum + 1
                int totalRows = sheet.getLastRowNum() + 1;
                if (totalRows == 0) {
                    return summary;
                }

                List<StockPrice> stockPrices = new ArrayList<>();

                for (int i = 1; i < totalRows; i++) {
                    StockPrice stockPrice = new StockPrice();
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        Cell cell1 = row.getCell(0); // first column - String stockCode
                        Cell cell2 = row.getCell(1); // second column - String stockExchange
                        Cell cell3 = row.getCell(2); // third column - BigDecimal currentPrice
                        Cell cell4 = row.getCell(3); // forth column - LocalDateTime date
                        Cell cell5 = row.getCell(4); // fifth column - LocalDateTime time

                        if (cell1 == null || cell2 == null || cell3 == null || cell4 == null || cell5 == null) {
                            break;
                        }

                        if (!CellType.STRING.equals(cell1.getCellType())) {
                            throw new RuntimeException("Company Code must be String");
                        }

                        stockPrice.setCompanyCode(cell1.getStringCellValue().trim().replaceAll("\\u00A0", ""));
                        stockPrice.setStockExchange(cell2.getStringCellValue().trim());

                        // check if IPO is available
                        IpoDetailsDto ipo = findIpoByCompanyCodeAndStockExchange(stockPrice.getCompanyCode(), stockPrice.getStockExchange());
                        if (ipo == null) {
                            throw new RuntimeException("Company " + stockPrice.getCompanyCode() + " cannot be found in stock exchange " + stockPrice.getStockExchange());
                        }

                        stockPrice.setCurrentPrice((float) cell3.getNumericCellValue());

                        String cell4Str = simpleShortDateFormat.format(cell4.getDateCellValue());
                        String cell5Str = cell5.getStringCellValue();
                        String stockDateTimestampStr = cell4Str.trim() + " " + cell5Str.trim();
                        Date stockDateTimestamp = simpleDateFormat.parse(stockDateTimestampStr);
                        stockPrice.setStockTimestamp(stockDateTimestamp);

                        Optional<StockPrice> optional = stockPriceRepository.findByCompanyCodeAndStockExchangeAndStockTimestamp(
                                stockPrice.getCompanyCode(), stockPrice.getStockExchange(), stockPrice.getStockTimestamp()
                        );
                        optional.ifPresent(s -> stockPrice.setId(s.getId()));

                        stockPrices.add(stockPrice);

                        if (summary.getCompanyCode() == null) {
                            summary.setCompanyName(ipo.getCompanyName());
                            summary.setCompanyCode(stockPrice.getCompanyCode());
                            summary.setStockExchange(stockPrice.getStockExchange());
                            summary.setDateFrom(stockPrice.getStockTimestamp());
                            summary.setDateTo(stockPrice.getStockTimestamp());
                        } else {
                            if (stockPrice.getStockTimestamp().before(summary.getDateFrom())) {
                                summary.setDateFrom(stockPrice.getStockTimestamp());
                            }
                            if (stockPrice.getStockTimestamp().after((summary.getDateTo()))) {
                                summary.setDateTo(stockPrice.getStockTimestamp());
                            }
                        }
                    }
                }

                summary.setTotalImported(stockPrices.size());

                if (stockPrices.size() > 0) {
                    stockPriceRepository.saveAll(stockPrices);
                }

            } else {
                return summary;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return summary;
    }

    @Override
    public StockPriceChartDto generateChartFor(
            String companyCode, String stockExchange, Date dateFrom, Date dateTo) {

        StockPriceChartDto stockPriceChartDto = new StockPriceChartDto();

        IpoDetailsDto ipoDetailsDto = findIpoByCompanyCodeAndStockExchange(companyCode, stockExchange);
        if (ipoDetailsDto == null) return stockPriceChartDto;

        stockPriceChartDto.setCompanyName(ipoDetailsDto.getCompanyName());
        stockPriceChartDto.setCompanyCode(companyCode);
        stockPriceChartDto.setStockExchange(stockExchange);

        if (dateFrom == null) dateFrom = new Date();
        if (dateTo == null) dateTo = new Date();

        List<StockPrice> list = stockPriceRepository.findAllByCompanyCodeAndStockExchangeAndStockTimestampBetween(
                companyCode, stockExchange, dateFrom, dateTo
        );

        List<StockPriceDateDto> stockPriceDateDtoList = list.stream().map(sp -> {
            StockPriceDateDto stockPriceDateDto = new StockPriceDateDto();
            BeanUtils.copyProperties(sp, stockPriceDateDto);
            return stockPriceDateDto;
        }).collect(Collectors.toList());

        stockPriceChartDto.setList(stockPriceDateDtoList);

        return stockPriceChartDto;
    }

    private IpoDetailsDto findIpoByCompanyCodeAndStockExchange(String companyCode, String stockExchange) {
        ResponseResult<IpoDetailsDto> res = companyClient.findIpoByCompanyCodeAndStockExchange(companyCode, stockExchange);
        return res.getCode() == 0 ? res.getData() : null;
    }

    private StockPriceDto convertToDto(StockPrice stockPrice) {
        StockPriceDto stockPriceDto = new StockPriceDto();
        BeanUtils.copyProperties(stockPrice, stockPriceDto);
        return stockPriceDto;
    }
}

