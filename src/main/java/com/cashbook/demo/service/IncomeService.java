package com.cashbook.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cashbook.demo.model.Income;

@Service
public interface IncomeService {

	List<Income> findAll();

	Income save(Income income);

	Income update(Income income);

	void delete(int incomeId);

	void deleteAll();

	Income findById(int id);

	int getTotal();

}
