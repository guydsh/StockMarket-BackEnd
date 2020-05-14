package com.smc.sector.service.impl;

import com.smc.sector.dto.PageDto;
import com.smc.sector.dto.SectorDto;
import com.smc.sector.entity.Sector;
import com.smc.sector.repository.SectorRepository;
import com.smc.sector.service.IPageService;
import com.smc.sector.service.ISectorService;
import com.smc.sector.vo.SectorVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SectorServiceImpl implements ISectorService {

    @Autowired
    private IPageService pageService;

    @Autowired
    private SectorRepository sectorRepository;

    @Override
    public void add(SectorVo sectorVo) {
        Sector sector = convertToEntity(null, sectorVo);
        sectorRepository.save(sector);
    }

    @Override
    public void update(Long id, SectorVo sectorVo) {
        Sector sector = convertToEntity(id, sectorVo);
        sectorRepository.save(sector);
    }

    @Override
    public PageDto findAll(Pageable pageable) {
        Page<Sector> page = sectorRepository.findAll(pageable);
        return pageService.convertToPageDto(page, this::convertToDto);
    }

    private Sector convertToEntity(Long id, SectorVo sectorVo) {
        Sector sector = new Sector();
        BeanUtils.copyProperties(sectorVo, sector);

        if (id != null) {
            Optional<Sector> optional = sectorRepository.findById(id);
            optional.ifPresent(s -> {
                sector.setId(s.getId());
                sector.setCreatedBy(s.getCreatedBy());
                sector.setCreatedTime(s.getCreatedTime());
                sector.setUpdatedBy(s.getUpdatedBy());
                sector.setUpdatedTime(new Date());
            });
        }

        return sector;
    }

    private SectorDto convertToDto(Sector sector) {
        SectorDto sectorDto = new SectorDto();
        BeanUtils.copyProperties(sector, sectorDto);

        return sectorDto;
    }
}
