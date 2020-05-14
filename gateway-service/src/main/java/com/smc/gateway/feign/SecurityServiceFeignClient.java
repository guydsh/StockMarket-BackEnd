package com.smc.gateway.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public interface SecurityServiceFeignClient {

    @FeignClient(name = "user-service", configuration = FeignConfiguration.class)
    interface AuthClient {
        @GetMapping(value = "/auth/me")
        String me();
    }

    class FeignConfiguration implements RequestInterceptor {

        @Override
        public void apply(RequestTemplate requestTemplate) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String token = request.getHeader("Authorization");
            requestTemplate.header("Authorization", token);
            requestTemplate.header("sourceRequest", request.getRequestURI());
            requestTemplate.header("sourceRequestMethod", request.getMethod());
        }
    }
}
