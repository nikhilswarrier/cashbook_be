package com.cashbook.demo.serviceimpl;

import static com.cashbook.demo.utils.ErrorMessage.FIELD_NOT_FOUND;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashbook.demo.config.ExpenditureConverter;
import com.cashbook.demo.exception.BadRequestException;
import com.cashbook.demo.model.Expenditure;
import com.cashbook.demo.model.ExpenditureDTO;
import com.cashbook.demo.repository.ExpenditureRepository;
import com.cashbook.demo.service.ExpenditureService;

@Service
public class ExpenditureServiceImpl implements ExpenditureService {
    @Autowired
    private ExpenditureRepository expenditureRepository;

    @Autowired
    private ExpenditureConverter expenditureConverter;

    public List<ExpenditureDTO> findAll() {
        List<ExpenditureDTO> expenditures = expenditureConverter.convert(expenditureRepository.findAll());
        for (ExpenditureDTO expenditure : expenditures) {
            expenditure.setOpeningBalance(0);
            expenditure.setTotalAmount(expenditureRepository.getTotal());
        }
        return expenditures;
    }

    @Override
    public ExpenditureDTO save(Expenditure expenditure) {

        if (expenditure.getId() != null && expenditureRepository.existsById(expenditure.getId())) {
            throw new EntityExistsException("There is already existing entity with such ID in the database.");
        }
        ExpenditureDTO saveExpense = expenditureConverter.convert(expenditureRepository.save(expenditure));
        saveExpense.setOpeningBalance(0);
        saveExpense.setTotalAmount(expenditureRepository.getTotal());
        return saveExpense;
    }

    @Override
    public ExpenditureDTO update(Expenditure expenditure) {

        if (expenditure.getId() != null && !expenditureRepository.existsById(expenditure.getId())) {
            throw new EntityExistsException("There is no entity with such ID in the database.");
        }
        ExpenditureDTO updateExpense = expenditureConverter.convert(expenditureRepository.save(expenditure));
        updateExpense.setOpeningBalance(0);
        updateExpense.setTotalAmount(expenditureRepository.getTotal());
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
    public ExpenditureDTO findById(int id) {
        Optional<Expenditure> hasExpenditure = expenditureRepository.findById(id);
        if (!hasExpenditure.isPresent())
            throw new BadRequestException(FIELD_NOT_FOUND);
        Expenditure expenditure = hasExpenditure.get();
        return expenditureConverter.convert(expenditure);
    }

}
