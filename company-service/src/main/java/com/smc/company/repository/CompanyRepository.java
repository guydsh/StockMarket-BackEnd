package com.smc.company.repository;

import com.smc.company.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Page<Company> findAllByCompanyNameContainingIgnoreCase(String keyword, Pageable pageable);
}
