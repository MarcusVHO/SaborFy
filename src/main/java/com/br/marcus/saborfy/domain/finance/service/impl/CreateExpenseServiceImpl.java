package com.br.marcus.saborfy.domain.finance.service.impl;

import com.br.marcus.saborfy.domain.finance.dto.request.CreateExpenseRequest;
import com.br.marcus.saborfy.domain.finance.entity.Expense;
import com.br.marcus.saborfy.domain.finance.repository.ExpenseRepository;
import com.br.marcus.saborfy.domain.finance.service.CreateExpenseService;
import org.springframework.stereotype.Service;

@Service
public class CreateExpenseServiceImpl implements CreateExpenseService {
    private final ExpenseRepository expenseRepository;

    public CreateExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }
    @Override
    public Expense createExpense(CreateExpenseRequest request) {
        Expense expense = new Expense();
        expense.setName(request.name());
        expense.setValue(request.value());
        expenseRepository.save(expense);
        return expense;
    }
}
