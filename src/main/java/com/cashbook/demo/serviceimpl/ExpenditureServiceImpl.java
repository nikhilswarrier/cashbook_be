package com.cashbook.demo.serviceimpl;

import static com.cashbook.demo.utils.ErrorMessage.FIELD_NOT_FOUND;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashbook.demo.exception.BadRequestException;
import com.cashbook.demo.model.Expenditure;
import com.cashbook.demo.repository.ExpenditureRepository;
import com.cashbook.demo.service.ExpenditureService;

@Service
public class ExpenditureServiceImpl implements ExpenditureService {
	@Autowired
	private ExpenditureRepository expenditureRepository;

	public List<Expenditure> findAll() {
		List<Expenditure> expenditures = expenditureRepository.findAll();
		return expenditures;
	}

	@Override
	public Expenditure save(Expenditure expenditure) {

		if (expenditure.getId() != null && expenditureRepository.existsById(expenditure.getId())) {
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}
		Expenditure saveExpense = expenditureRepository.save(expenditure);
		return saveExpense;
	}

	@Override
	public Expenditure update(Expenditure expenditure) {

		if (expenditure.getId() != null && !expenditureRepository.existsById(expenditure.getId())) {
			throw new EntityExistsException("There is no entity with such ID in the database.");
		}
		Expenditure updateExpense = expenditureRepository.save(expenditure);
		return updateExpense;
	}

	@Override
	public void delete(int id) {
		expenditureRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		expenditureRepository.deleteAll();
	}

	@Override
	public Expenditure findById(int id) {
		Optional<Expenditure> hasExpenditure = expenditureRepository.findById(id);
		if (!hasExpenditure.isPresent())
			throw new BadRequestException(FIELD_NOT_FOUND);
		Expenditure expenditure = hasExpenditure.get();
		return  expenditure;
	}

}
