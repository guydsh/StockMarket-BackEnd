package com.smc.se.repository;

import com.smc.se.entity.StockExchange;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface StockExchangeRepository extends JpaRepository<StockExchange, Long> {
}
