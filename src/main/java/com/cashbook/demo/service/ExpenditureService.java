package com.cashbook.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cashbook.demo.model.Expenditure;
import com.cashbook.demo.model.ExpenditureDTO;

@Service
public interface ExpenditureService {
    List<ExpenditureDTO> findAll();

	ExpenditureDTO save(Expenditure expenditure);

	ExpenditureDTO update(Expenditure expenditure);

	void delete(int id);

	void deleteAll();

	ExpenditureDTO findById(int id);

}
