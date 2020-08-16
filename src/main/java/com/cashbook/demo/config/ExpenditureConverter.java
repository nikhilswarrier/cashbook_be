package com.cashbook.demo.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import com.cashbook.demo.model.Expenditure;
import com.cashbook.demo.model.ExpenditureDTO;

@Component
public class ExpenditureConverter implements Converter<ExpenditureDTO,Expenditure> {
    
     @Override
    public Expenditure convert(ExpenditureDTO source) {
        Expenditure target = new Expenditure();
        target.setId(source.getId());
        target.setDate(source.getDate());
        target.setParticulars(source.getParticulars());
        target.setVrNumber(source.getVrNumber());
        target.setAmount(source.getAmount());
        return target;
    }

    public ExpenditureDTO convert(Expenditure source) {
        ExpenditureDTO target = new ExpenditureDTO();
        target.setId(source.getId());
        target.setDate(source.getDate());
        target.setParticulars(source.getParticulars());
        target.setVrNumber(source.getVrNumber());
        target.setAmount(source.getAmount());
        return target;
    }

    public List<ExpenditureDTO> convert(List<Expenditure> source) {
        return source.stream().map(this::convert).collect(Collectors.toList());
    }

    public List<Expenditure> convertTO(List<ExpenditureDTO> source) {
        return source.stream().map(this::convert).collect(Collectors.toList());
    }
}