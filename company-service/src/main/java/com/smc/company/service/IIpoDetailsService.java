package com.smc.company.service;

import com.smc.company.dto.IpoDetailsDto;
import com.smc.company.dto.PageDto;
import com.smc.company.vo.IpoDetailsVo;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IIpoDetailsService {
    PageDto<IpoDetailsDto> getAll(Pageable pageable);
    PageDto<IpoDetailsDto> getFutureIpos(Pageable pageable);
    void add(IpoDetailsVo ipoDetailsVo);
    void update(Long id, IpoDetailsVo ipoDetailsVo);
    Optional<IpoDetailsDto> findByCompanyCodeAndStockExchange(String companyName, String stockExchange);
}
