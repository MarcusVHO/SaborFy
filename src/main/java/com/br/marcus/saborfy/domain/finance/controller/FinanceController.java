package com.br.marcus.saborfy.domain.finance.controller;

import com.br.marcus.saborfy.domain.finance.dto.response.FinanceResponse;
import com.br.marcus.saborfy.domain.finance.dto.request.CreateExpenseRequest;
import com.br.marcus.saborfy.domain.finance.entity.Expense;
import com.br.marcus.saborfy.domain.finance.enums.FinanceType;
import com.br.marcus.saborfy.domain.finance.service.CreateExpenseService;
import com.br.marcus.saborfy.domain.finance.service.FinanceService;
import com.br.marcus.saborfy.domain.finance.service.RevenueAndExpenseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(path = "/finance")
public class FinanceController {
    private final FinanceService financeService;
    private final RevenueAndExpenseService revenueAndExpenseService;
    private final CreateExpenseService createExpenseService;

    public FinanceController(FinanceService financeService, RevenueAndExpenseService revenueAndExpenseService, CreateExpenseService createExpenseService) {
        this.financeService = financeService;
        this.revenueAndExpenseService = revenueAndExpenseService;
        this.createExpenseService = createExpenseService;
    }

    @GetMapping
    public ResponseEntity<List<FinanceResponse>> get(
            @RequestParam @NotNull(message = "Start date is necessary!")Instant startDate,
            @RequestParam @NotNull(message = "End date is necessary!") Instant endDate
            ) {
        return ResponseEntity.ok(financeService.getFinance(startDate, endDate));
    }

    @GetMapping(path = "/revenue")
    public ResponseEntity<BigDecimal> getRevue(
            @RequestParam @NotNull(message = "Start date is necessary!")Instant startDate,
            @RequestParam @NotNull(message = "End date is necessary!") Instant endDate
            ) {
        return ResponseEntity.ok(revenueAndExpenseService.getRevenue(startDate, endDate));
    }

    @GetMapping(path = "/expense")
    public ResponseEntity<BigDecimal> getExpense(
            @RequestParam @NotNull(message = "Start date is necessary!")Instant startDate,
            @RequestParam @NotNull(message = "End date is necessary!") Instant endDate
            ) {
        return ResponseEntity.ok(revenueAndExpenseService.getExpense(startDate, endDate));
    }

    @GetMapping(path = "/avg")
    public ResponseEntity<BigDecimal> getAvg(
            @RequestParam @NotNull(message = "Start date is necessary!")Instant startDate,
            @RequestParam @NotNull(message = "End date is necessary!") Instant endDate
            ) {
        return ResponseEntity.ok(revenueAndExpenseService.getAvg(startDate, endDate));
    }

    @PostMapping(path = "expense")
    public ResponseEntity<FinanceResponse> post(
            @RequestBody @Valid CreateExpenseRequest request
            ) {
        Expense expense = createExpenseService.createExpense(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new FinanceResponse(expense.getName(), expense.getValue(), expense.getCreateAt(), FinanceType.EXPENSE));
    }
}
