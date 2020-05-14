package com.smc.company.controller;

import com.smc.company.dto.CompanyDto;
import com.smc.company.dto.PageDto;
import com.smc.company.dto.ResponseResult;
import com.smc.company.service.ICompanyService;
import com.smc.company.vo.CompanyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/companies")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @GetMapping
    public ResponseResult<PageDto<CompanyDto>> findAllCompanies(
            @RequestParam(name = "q", defaultValue = "", required = false) String keyword,
            @PageableDefault(sort = {"updatedTime"}) Pageable pageable) {

        PageDto<CompanyDto> page = companyService.getAll(keyword, pageable);

        return ResponseResult.success("Execute successfully", page);
    }

    @PostMapping
    public ResponseResult createCompany(@RequestBody CompanyVo companyVo) {

        companyService.add(companyVo);

        return ResponseResult.success("Execute successfully", null);
    }

    @PutMapping("/{id}")
    public ResponseResult createCompany(@PathVariable(value = "id") Long id,
                                        @RequestBody CompanyVo companyVo) {

        companyService.update(id, companyVo);

        return ResponseResult.success("Execute successfully", null);
    }

    @PutMapping("/{id}/activation")
    public ResponseResult updateStatus(@PathVariable(value = "id") Long id,
                                        @RequestParam(name = "active") boolean active) {

        companyService.setStatus(id, active);

        return ResponseResult.success("Execute successfully", null);
    }

}
