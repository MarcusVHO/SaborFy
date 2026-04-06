package com.br.marcus.saborfy.domain.payment.controller;

import com.br.marcus.saborfy.domain.payment.dto.response.DashboardResponse;
import com.br.marcus.saborfy.domain.payment.service.DashboardService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping(path = "/payment/dashboard")
public class PaymentDashboardController {

    private final DashboardService dashboardService;

    public PaymentDashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public DashboardResponse get(
            @RequestParam @NotNull(message = "Start date is necessary!") Instant startDate,
            @RequestParam @NotNull(message = "End date is necessary! ") Instant endDate
    ) {
        return dashboardService.getDashboard(startDate, endDate);
    }

}
