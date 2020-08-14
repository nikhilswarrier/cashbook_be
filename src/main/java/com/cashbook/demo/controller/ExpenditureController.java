package com.cashbook.demo.controller;


import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cashbook.demo.model.Expenditure;
import com.cashbook.demo.service.ExpenditureService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("expense")
public class ExpenditureController {
	@Autowired
	private ExpenditureService expenditureService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<Expenditure> getAllItems() {
		return expenditureService.findAll();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Expenditure addIntoExpenditure(@RequestBody Expenditure expenditure) {
		return expenditureService.save(expenditure);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public Expenditure updateExpenditure(@RequestBody Expenditure expenditure) throws URISyntaxException {
		return expenditureService.update(expenditure);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public Expenditure findById(@PathVariable int id) {
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
}