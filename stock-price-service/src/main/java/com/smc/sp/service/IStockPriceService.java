package com.smc.sp.service;

import com.smc.sp.dto.*;
import com.smc.sp.entity.StockPrice;
import com.smc.sp.vo.StockPriceChartVo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface IStockPriceService {
    PageDto<StockPriceDto> findAll(Pageable pageable);
    Summary importStockPrices(MultipartFile file);
    StockPriceChartDto generateChartFor(
            String companyCode,
            String stockExchange,
            Date dateFrom,
            Date dateTo
    );
}
