package com.smc.company.service.impl;

import com.smc.company.dto.IpoDetailsDto;
import com.smc.company.dto.PageDto;
import com.smc.company.entity.IpoDetails;
import com.smc.company.repository.IpoDetailsRepository;
import com.smc.company.service.IIpoDetailsService;
import com.smc.company.service.IPageService;
import com.smc.company.vo.IpoDetailsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class IpoDetailsServiceImpl implements IIpoDetailsService {

    @Autowired
    private IpoDetailsRepository ipoDetailsRepository;

    @Autowired
    private IPageService pageService;

    @Override
    public PageDto<IpoDetailsDto> getAll(Pageable pageable) {
        Page<IpoDetails> page = ipoDetailsRepository.findAll(pageable);
        return pageService.convertToPageDto(page, this::convertToDto);
    }

    @Override
    public PageDto<IpoDetailsDto> getFutureIpos(Pageable pageable) {
        Page<IpoDetails> page = ipoDetailsRepository.findAllByOpenDatetimeAfter(new Date(), pageable);
        return pageService.convertToPageDto(page, this::convertToDto);
    }

    @Override
    public void add(IpoDetailsVo ipoDetailsVo) {
        IpoDetails ipoDetails = convertToEntity(null, ipoDetailsVo);
        ipoDetailsRepository.save(ipoDetails);
    }

    @Override
    public void update(Long id, IpoDetailsVo ipoDetailsVo) {
        IpoDetails ipoDetails = convertToEntity(id, ipoDetailsVo);
        ipoDetailsRepository.save(ipoDetails);
    }

    @Override
    public Optional<IpoDetailsDto> findByCompanyCodeAndStockExchange(String companyCode, String stockExchange) {
        Optional<IpoDetails> optional = ipoDetailsRepository.findByCompanyCodeAndStockExchangeName(companyCode, stockExchange);
        if (optional.isPresent()) {
            return Optional.of(convertToDto(optional.get()));
        }
        return Optional.empty();
    }

    private IpoDetails convertToEntity(Long id, IpoDetailsVo ipoDetailsVo) {
        IpoDetails ipoDetails = new IpoDetails();
        BeanUtils.copyProperties(ipoDetailsVo, ipoDetails);

        if (id != null) {
            Optional<IpoDetails> optional = ipoDetailsRepository.findById(id);
            optional.ifPresent(i -> {
                ipoDetails.setId(i.getId());
                ipoDetails.setCreatedBy(i.getCreatedBy());
                ipoDetails.setCreatedTime(i.getCreatedTime());
                ipoDetails.setUpdatedBy(i.getUpdatedBy());
                ipoDetails.setUpdatedTime(new Date());
            });
        }

        return ipoDetails;
    }

    private IpoDetailsDto convertToDto(IpoDetails ipoDetails) {
        IpoDetailsDto ipoDetailsDto = new IpoDetailsDto();
        BeanUtils.copyProperties(ipoDetails, ipoDetailsDto);
        return ipoDetailsDto;
    }
}
