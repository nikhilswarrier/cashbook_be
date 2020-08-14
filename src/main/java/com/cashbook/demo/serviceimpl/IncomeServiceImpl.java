package com.cashbook.demo.serviceimpl;

import static com.cashbook.demo.utils.ErrorMessage.FIELD_NOT_FOUND;
import static com.cashbook.demo.utils.ErrorMessage.ALREADY_EXISTING_ENTITY;
import static com.cashbook.demo.utils.ErrorMessage.NO_ENTITY_FOUND;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashbook.demo.exception.BadRequestException;
import com.cashbook.demo.model.Income;
import com.cashbook.demo.repository.IncomeRepository;
import com.cashbook.demo.service.IncomeService;

@Service
public class IncomeServiceImpl implements IncomeService {
	@Autowired
	private IncomeRepository incomeRepository;

	@Override
	public List<Income> findAll() {
		List<Income> incomes = incomeRepository.findAll();
		return incomes;
	}

	@Override
	public Income save(Income income) {
		if (income.getId() != null && incomeRepository.existsById(income.getId())) {
			throw new EntityExistsException(ALREADY_EXISTING_ENTITY);
		}
		Income response = incomeRepository.save(income);
		return response;
	}

	@Override
	public Income update(Income income) {
		if (income.getId() != null && !incomeRepository.existsById(income.getId())) {
			throw new EntityExistsException(NO_ENTITY_FOUND);
		}
		Income response = incomeRepository.save(income);
		return response;
	}

	@Override
	public void delete(int incomeId) {
		Optional<Income> hasIncome = incomeRepository.findById(incomeId);
		if (!hasIncome.isPresent())
			throw new BadRequestException(FIELD_NOT_FOUND);
		incomeRepository.deleteById(incomeId);
	}

	@Override
	public void deleteAll() {
		incomeRepository.deleteAll();
	}

	@Override
	public Income findById(int id) {
		Optional<Income> hasIncome = incomeRepository.findById(id);
		if (!hasIncome.isPresent())
			throw new BadRequestException(FIELD_NOT_FOUND);
		Income income = hasIncome.get();
		return income;
	}

	@Override
	public int getTotal() {
		return incomeRepository.getTotal();
	}

}
