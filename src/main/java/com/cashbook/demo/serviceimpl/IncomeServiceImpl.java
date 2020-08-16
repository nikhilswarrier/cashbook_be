package com.cashbook.demo.serviceimpl;

import static com.cashbook.demo.utils.ErrorMessage.FIELD_NOT_FOUND;
import static com.cashbook.demo.utils.ErrorMessage.ALREADY_EXISTING_ENTITY;
import static com.cashbook.demo.utils.ErrorMessage.NO_ENTITY_FOUND;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashbook.demo.config.IncomeConverter;
import com.cashbook.demo.exception.BadRequestException;
import com.cashbook.demo.model.Income;
import com.cashbook.demo.model.IncomeDTO;
import com.cashbook.demo.repository.ExpenditureRepository;
import com.cashbook.demo.repository.IncomeRepository;
import com.cashbook.demo.service.IncomeService;

@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenditureRepository expenditureRepository;

    @Autowired
    private IncomeConverter incomeConverter;

    @Override
    public List<IncomeDTO> findAll() {
        List<IncomeDTO> incomes = incomeConverter.convert(incomeRepository.findAll());
        for (IncomeDTO income : incomes) {
            income.setTotalAmount(incomeRepository.getTotal());
            income.setOpeningBalance(0);
            income.setCumulativeBalance(findCumulative());
        }
        return incomes;
    }

    @Override
    public IncomeDTO save(Income income) {
        if (income.getId() != null && incomeRepository.existsById(income.getId())) {
            throw new EntityExistsException(ALREADY_EXISTING_ENTITY);
        }
        IncomeDTO response = incomeConverter.convert(incomeRepository.save(income));
        response.setTotalAmount(incomeRepository.getTotal());
        response.setOpeningBalance(0);
        response.setCumulativeBalance(incomeRepository.getTotal());

        return response;
    }

    @Override
    public IncomeDTO update(Income income) {
        if (income.getId() != null && !incomeRepository.existsById(income.getId())) {
            throw new EntityExistsException(NO_ENTITY_FOUND);
        }
        IncomeDTO response = incomeConverter.convert(incomeRepository.save(income));
        response.setTotalAmount(incomeRepository.getTotal());
        response.setOpeningBalance(0);
        response.setCumulativeBalance(findCumulative());
        System.out.println(response.getCumulativeBalance());
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
    public IncomeDTO findById(int id) {
        Optional<Income> hasIncome = incomeRepository.findById(id);
        if (!hasIncome.isPresent())
            throw new BadRequestException(FIELD_NOT_FOUND);
        Income income = hasIncome.get();
        return incomeConverter.convert(income);
    }

    private float findCumulative() {
        return incomeRepository.getTotal() - expenditureRepository.getTotal();
    }

}
