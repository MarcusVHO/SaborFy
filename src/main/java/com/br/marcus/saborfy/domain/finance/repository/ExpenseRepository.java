package com.br.marcus.saborfy.domain.finance.repository;

import com.br.marcus.saborfy.domain.finance.dto.response.FinanceResponse;
import com.br.marcus.saborfy.domain.finance.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("""
      SELECT new com.br.marcus.saborfy.domain.finance.dto.response.FinanceResponse(
        e.name,
        e.value,
        e.createAt,
        com.br.marcus.saborfy.domain.finance.enums.FinanceType.EXPENSE
      )
      FROM Expense e
      WHERE e.createAt BETWEEN :startDate AND :endDate
""")
    List<FinanceResponse> findAllExpensesFinance(
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate
    );

    @Query(""" 
       SELECT SUM (p.value)
       FROM Expense p
       WHERE p.createAt BETWEEN :startDate AND :endDate
   
    """)
    BigDecimal sumByPeriod(
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate
    );
}
