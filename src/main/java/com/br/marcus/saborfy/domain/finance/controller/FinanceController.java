package com.br.marcus.saborfy.domain.finance.controller;

import com.br.marcus.saborfy.domain.finance.dto.FinanceResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/finance")
public class FinanceController {
    @GetMapping
    public ResponseEntity<FinanceResponse> get() {
        return null;
    }
}
