package com.smc.sp.feign;

import com.smc.sp.dto.IpoDetailsDto;
import com.smc.sp.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface FeignClients {

    @FeignClient(name = "company-service")
    interface CompanyClient {
        @GetMapping(value = "/api/v1/ipos/byCompanyCodeAndStockExchange")
        ResponseResult<IpoDetailsDto> findIpoByCompanyCodeAndStockExchange(
                @RequestParam(name = "companyCode") String companyCode,
                @RequestParam(name = "stockExchange") String stockExchange
        );
    }

}
