package com.cashbook.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cashbook.demo.model.Income;
import com.cashbook.demo.model.IncomeDTO;

@Service
public interface IncomeService {

    List<IncomeDTO> findAll();

	IncomeDTO save(Income income);

	IncomeDTO update(Income income);

	void delete(int incomeId);

	void deleteAll();

	IncomeDTO findById(int id);

}
