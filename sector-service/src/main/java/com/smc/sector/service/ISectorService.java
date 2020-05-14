package com.smc.sector.service;

import com.smc.sector.dto.PageDto;
import com.smc.sector.vo.SectorVo;
import org.springframework.data.domain.Pageable;

public interface ISectorService {
    void add(SectorVo sectorVo);
    void update(Long id, SectorVo sectorVo);
    PageDto findAll(Pageable pageable);
}
