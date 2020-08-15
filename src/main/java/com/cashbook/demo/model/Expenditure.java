package com.cashbook.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expenditure")
public class Expenditure {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private Date date;

	private String particulars;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private int vrNumber;

	private float amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public int getVrNumber() {
		return vrNumber;
	}

	public void setVrNumber(int vrNumber) {
		this.vrNumber = vrNumber;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	
}
