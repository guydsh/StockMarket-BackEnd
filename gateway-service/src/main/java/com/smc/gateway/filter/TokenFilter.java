package com.smc.gateway.filter;

import com.smc.gateway.feign.SecurityServiceFeignClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;


@Component
public class TokenFilter extends ZuulFilter {

    @Autowired
    private SecurityServiceFeignClient.AuthClient authClient;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        return !antPathMatcher.match("/*/api/v1/login", request.getRequestURI())
                && !antPathMatcher.match("/*/api/v1/registration/**", request.getRequestURI());
    }

    @Override
    public Object run() throws ZuulException {
        try{
            String me = authClient.me();
            return me;
        } catch (Exception e) {
            throw new ZuulException(e.getMessage(), 0, e.getMessage());
        }
    }
}
