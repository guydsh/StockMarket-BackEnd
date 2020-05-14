package com.smc.company.controller;

import com.smc.company.dto.IpoDetailsDto;
import com.smc.company.dto.PageDto;
import com.smc.company.dto.ResponseResult;
import com.smc.company.service.IIpoDetailsService;
import com.smc.company.vo.IpoDetailsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/ipos")
public class IpoController {

    @Autowired
    private IIpoDetailsService ipoDetailsService;

    @GetMapping
    public ResponseResult<PageDto<IpoDetailsDto>> findAllIpos(
            @PageableDefault(sort = {"updatedTime"}) Pageable pageable) {

        PageDto<IpoDetailsDto> pageDto = ipoDetailsService.getAll(pageable);

        return ResponseResult.success("Execute successfully", pageDto);
    }

    @GetMapping("/future")
    public ResponseResult<PageDto<IpoDetailsDto>> findFutureIpos(
            @PageableDefault(sort = {"updatedTime"}) Pageable pageable
    ) {
        PageDto<IpoDetailsDto> pageDto = ipoDetailsService.getFutureIpos(pageable);

        return ResponseResult.success("Execute successfully", pageDto);
    }

    @PostMapping
    public ResponseResult createIpo(@RequestBody IpoDetailsVo ipoDetailsVo) {

        ipoDetailsService.add(ipoDetailsVo);

        return ResponseResult.success("Execute successfully", null);
    }

    @PutMapping("/{id}")
    public ResponseResult createIpo(
            @PathVariable(value = "id") Long id,
            @RequestBody IpoDetailsVo ipoDetailsVo) {

        ipoDetailsService.update(id, ipoDetailsVo);

        return ResponseResult.success("Execute successfully", null);
    }

    @GetMapping("/byCompanyCodeAndStockExchange")
    public ResponseResult<Object> findByCompanyCodeAndStockExchange(
            @RequestParam(value = "companyCode") String companyCode,
            @RequestParam(value = "stockExchange") String stockExchange
    ) {
        Optional<IpoDetailsDto> optional = ipoDetailsService.findByCompanyCodeAndStockExchange(
                companyCode, stockExchange
        );

        if (optional.isPresent()) {
            return ResponseResult.success("get successfully", optional.get());
        } else {
            return ResponseResult.fail("failed");
        }

    }

}
