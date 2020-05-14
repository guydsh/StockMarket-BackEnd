package com.smc.se.controller;

import com.smc.se.dto.PageDto;
import com.smc.se.dto.ResponseResult;
import com.smc.se.dto.StockExchangeDto;
import com.smc.se.service.IStockExchangeService;
import com.smc.se.vo.StockExchangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/stock-exchanges")
public class StockExchangeController {

    @Autowired
    private IStockExchangeService seService;

    @GetMapping
    public ResponseResult<PageDto<StockExchangeDto>> findAllStockExchanges(
            @PageableDefault(sort = {"updatedTime"}) Pageable pageable) {

        PageDto<StockExchangeDto> page = seService.findAll(pageable);

        return ResponseResult.success("Execute successfully", page);
    }

    @PostMapping
    public ResponseResult createStockExchange(@RequestBody StockExchangeVo seVo) {

        seService.add(seVo);

        return ResponseResult.success("Execute successfully", null);
    }

    @PutMapping("/{id}")
    public ResponseResult updateStockExchange(@PathVariable(value = "id") Long id,
            @RequestBody StockExchangeVo seVo) {

        seService.update(id, seVo);

        return ResponseResult.success("Execute successfully", null);
    }

}
