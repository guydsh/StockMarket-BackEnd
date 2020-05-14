package com.smc.company.service;

import com.smc.company.dto.CompanyDto;
import com.smc.company.dto.PageDto;
import com.smc.company.vo.CompanyVo;

import org.springframework.data.domain.Pageable;

public interface ICompanyService {
    PageDto<CompanyDto> getAll(String keyword, Pageable pageable);
    void add(CompanyVo companyVo);
    void update(Long number, CompanyVo companyVo);
    void setStatus(Long number, boolean active);
}
