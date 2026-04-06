package com.br.marcus.saborfy.domain.payment.service;

import com.br.marcus.saborfy.domain.payment.dto.response.DashboardResponse;

import java.time.Instant;

public interface DashboardService {
    DashboardResponse getDashboard(Instant start, Instant endDate);
}
