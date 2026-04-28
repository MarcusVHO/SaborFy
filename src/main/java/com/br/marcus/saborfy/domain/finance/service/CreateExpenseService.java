package com.br.marcus.saborfy.domain.finance.service;

import com.br.marcus.saborfy.domain.finance.dto.request.CreateExpenseRequest;
import com.br.marcus.saborfy.domain.finance.entity.Expense;

public interface CreateExpenseService {
    Expense createExpense(CreateExpenseRequest request);
}
