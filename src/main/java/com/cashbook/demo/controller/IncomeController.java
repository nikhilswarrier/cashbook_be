package com.cashbook.demo.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.cashbook.demo.model.Income;
import com.cashbook.demo.service.IncomeService;

import lombok.RequiredArgsConstructor;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("income")
public class IncomeController {

	@Autowired
	private IncomeService incomeService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<Income> getAllItems() {
		return incomeService.findAll();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Income addIntoIncome(@RequestBody Income income) {
		return incomeService.save(income);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public Income updateIncome(@RequestBody Income income) throws URISyntaxException {
		return incomeService.update(income);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public Income findById(@PathVariable int id) {
		return incomeService.findById(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{incomeId}")
	public void delete(@PathVariable int incomeId) {
		incomeService.delete(incomeId);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping
	public void deleteAll() {
		incomeService.deleteAll();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("total")
	public int getTotal() {
		return incomeService.getTotal();
	}
}
