package com.smc.sector.controller;

import com.smc.sector.dto.PageDto;
import com.smc.sector.dto.ResponseResult;
import com.smc.sector.dto.SectorDto;
import com.smc.sector.service.ISectorService;
import com.smc.sector.vo.SectorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/sectors")
public class SectorController {

    @Autowired
    private ISectorService sectorService;

    @GetMapping
    public ResponseResult<PageDto<SectorDto>> findAllStockExchanges(
            @PageableDefault(sort = {"updatedTime"}) Pageable pageable) {

        PageDto<SectorDto> page = sectorService.findAll(pageable);

        return ResponseResult.success("Execute successfully", page);
    }

    @PostMapping
    public ResponseResult createStockExchange(@RequestBody SectorVo sectorVo) {

        sectorService.add(sectorVo);

        return ResponseResult.success("Execute successfully", null);
    }

    @PutMapping("/{id}")
    public ResponseResult updateStockExchange(@PathVariable(value = "id") Long id,
            @RequestBody SectorVo sectorVo) {

        sectorService.update(id, sectorVo);

        return ResponseResult.success("Execute successfully", null);
    }

}
