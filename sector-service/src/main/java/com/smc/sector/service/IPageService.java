package com.smc.sector.service;

import com.smc.sector.dto.PageDto;
import org.springframework.data.domain.Page;

public interface IPageService {
    <S, T> PageDto<T> convertToPageDto(Page<S> page, Converter<S, T> converter);
}
