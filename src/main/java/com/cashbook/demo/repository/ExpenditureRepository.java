package com.cashbook.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cashbook.demo.model.Expenditure;

@Repository
public interface ExpenditureRepository extends JpaRepository<Expenditure, Integer> {

    @Query("SELECT SUM(e.amount) from Expenditure e")
	int getTotal();
}
