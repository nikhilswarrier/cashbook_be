package com.cashbook.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cashbook.demo.model.Expenditure;

@Service
public interface ExpenditureService {
	List<Expenditure> findAll();

	Expenditure save(Expenditure expenditure);

	Expenditure update(Expenditure expenditure);

	void delete(int id);

	void deleteAll();

	Expenditure findById(int id);

}
