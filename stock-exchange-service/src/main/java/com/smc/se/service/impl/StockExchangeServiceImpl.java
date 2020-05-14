package com.smc.se.service.impl;

import com.smc.se.dto.PageDto;
import com.smc.se.dto.StockExchangeDto;
import com.smc.se.entity.StockExchange;
import com.smc.se.repository.StockExchangeRepository;
import com.smc.se.service.IPageService;
import com.smc.se.service.IStockExchangeService;
import com.smc.se.vo.StockExchangeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class StockExchangeServiceImpl implements IStockExchangeService {

    @Autowired
    private IPageService pageService;

    @Autowired
    private StockExchangeRepository seRepository;

    @Override
    public void add(StockExchangeVo seVo) {
        StockExchange se = convertToEntity(null, seVo);
        seRepository.save(se);
    }

    @Override
    public void update(Long id, StockExchangeVo seVo) {
        StockExchange se = convertToEntity(id, seVo);
        seRepository.save(se);
    }

    @Override
    public PageDto findAll(Pageable pageable) {
        Page<StockExchange> page = seRepository.findAll(pageable);
        return pageService.convertToPageDto(page, this::convertToDto);
    }

    private StockExchange convertToEntity(Long id, StockExchangeVo seVo) {
        StockExchange se = new StockExchange();
        BeanUtils.copyProperties(seVo, se);

        if (id != null) {
            Optional<StockExchange> optional = seRepository.findById(id);
            optional.ifPresent(oldSe -> {
                se.setId(oldSe.getId());
                se.setCreatedBy(oldSe.getCreatedBy());
                se.setCreatedTime(oldSe.getCreatedTime());
                se.setUpdatedBy(oldSe.getUpdatedBy());
                se.setUpdatedTime(new Date());
            });
        }

        return se;
    }

    private StockExchangeDto convertToDto(StockExchange se) {
        StockExchangeDto seDto = new StockExchangeDto();
        BeanUtils.copyProperties(se, seDto);

        return seDto;
    }
}
