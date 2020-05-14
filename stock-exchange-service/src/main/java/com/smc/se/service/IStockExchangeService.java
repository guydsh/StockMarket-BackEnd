package com.smc.se.service;

import com.smc.se.dto.PageDto;
import com.smc.se.vo.StockExchangeVo;
import org.springframework.data.domain.Pageable;

public interface IStockExchangeService {
    void add(StockExchangeVo seVo);
    void update(Long id, StockExchangeVo seVo);
    PageDto findAll(Pageable pageable);
}
