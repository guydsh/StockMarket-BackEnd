package com.smc.company.service.impl;

import com.smc.company.dto.CompanyDto;
import com.smc.company.dto.PageDto;
import com.smc.company.dto.StockExchangeDto;
import com.smc.company.entity.Company;
import com.smc.company.repository.CompanyRepository;
import com.smc.company.service.ICompanyService;
import com.smc.company.service.IPageService;
import com.smc.company.vo.CompanyVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private IPageService pageService;

    @Override
    public PageDto<CompanyDto> getAll(String keyword, Pageable pageable) {
        Page<Company> page = companyRepository.findAllByCompanyNameContainingIgnoreCase(
                keyword,
                pageable
        );
        return pageService.convertToPageDto(page, this::convertToDto);
    }

    @Override
    public void add(CompanyVo companyVo) {
        Company company = convertToEntity(null, companyVo);
        companyRepository.save(company);
    }

    @Override
    public void update(Long id, CompanyVo companyVo) {
        Company company = convertToEntity(id, companyVo);
        companyRepository.save(company);
    }

    @Override
    public void setStatus(Long id, boolean active) {
        Optional<Company> optional = companyRepository.findById(id);
        optional.ifPresent(company -> {
            company.setActive(active);
            companyRepository.save(company);
        });
    }

    private CompanyDto convertToDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        BeanUtils.copyProperties(company, companyDto);

//        List<StockExchangeDto> stockExchangeDtos = company.getStockExchanges()
//                .stream().map(stockExchange -> {
//                    StockExchangeDto stockExchangeDto = new StockExchangeDto();
//                    BeanUtils.copyProperties(stockExchange, stockExchangeDto);
//                    return stockExchangeDto;
//                }).collect(Collectors.toList());
//
//        companyDto.setStockExchanges(stockExchangeDtos);

        return companyDto;
    }

    private Company convertToEntity(Long id, CompanyVo companyVo) {
        Company company = new Company();
        BeanUtils.copyProperties(companyVo, company);

//        company.getStockExchanges().forEach(stockExchange -> stockExchange.setCompany(company));

        if (id != null) {
            Optional<Company> optional = companyRepository.findById(id);
            optional.ifPresent(c -> {
                company.setId(c.getId());
                company.setCreatedBy(c.getCreatedBy());
                company.setCreatedTime(c.getCreatedTime());
                company.setUpdatedBy(c.getUpdatedBy());
                company.setUpdatedTime(new Date());
            });
        }

        return company;
    }
}
