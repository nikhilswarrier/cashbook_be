package com.cashbook.demo.config;

import java.util.List;
import java.util.stream.Collectors;

import com.cashbook.demo.model.Income;
import com.cashbook.demo.model.IncomeDTO;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IncomeConverter implements Converter<IncomeDTO, Income> {

    @Override
    public Income convert(IncomeDTO source) {
        Income target = new Income();
        target.setId(source.getId());
        target.setDate(source.getDate());
        target.setParticulars(source.getParticulars());
        target.setVrNumber(source.getVrNumber());
        target.setAmount(source.getAmount());
        return target;
    }

    public IncomeDTO convert(Income source) {
        IncomeDTO target = new IncomeDTO();
        target.setId(source.getId());
        target.setDate(source.getDate());
        target.setParticulars(source.getParticulars());
        target.setVrNumber(source.getVrNumber());
        target.setAmount(source.getAmount());
        return target;
    }

    public List<IncomeDTO> convert(List<Income> source) {
        return source.stream().map(this::convert).collect(Collectors.toList());
    }

    public List<Income> convertTO(List<IncomeDTO> source) {
        return source.stream().map(this::convert).collect(Collectors.toList());
    }
}
