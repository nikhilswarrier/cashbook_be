package com.cashbook.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cashbook.demo.model.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Integer> {

	@Query("SELECT SUM(i.amount) from Income i")
	int getTotal();
	

}
