package com.smc.company.repository;

import com.smc.company.entity.IpoDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface IpoDetailsRepository extends JpaRepository<IpoDetails, Long> {
    Optional<IpoDetails> findByCompanyCodeAndStockExchangeName(String companyCode, String stockExchangeName);

    Page<IpoDetails> findAllByOpenDatetimeAfter(Date date, Pageable pageable);
}
