package com.cashbook.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cashbook.demo.config.GeneratePdfReportExpense;
import com.cashbook.demo.model.Expenditure;
import com.cashbook.demo.service.ExpenditureService;

import lombok.RequiredArgsConstructor;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("expense")
public class ExpenditureController {
    @Autowired
    private ExpenditureService expenditureService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ExpenditureDTO> getAllItems() {
        return expenditureService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ExpenditureDTO addIntoExpenditure(@RequestBody Expenditure expenditure) {
        return expenditureService.save(expenditure);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public ExpenditureDTO updateExpenditure(@RequestBody Expenditure expenditure) throws URISyntaxException {
        return expenditureService.update(expenditure);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ExpenditureDTO findById(@PathVariable int id) {
        return expenditureService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        expenditureService.delete(id);

    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void deleteAll() {
        expenditureService.deleteAll();

    }

    @GetMapping(value = "/pdfreport", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> incomeReport() throws IOException {

        List<ExpenditureDTO> expenses = (List<ExpenditureDTO>) expenditureService.findAll();

        ByteArrayInputStream bis = GeneratePdfReportExpense.expenseReport(expenses);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=expensereport.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
