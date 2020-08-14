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

	@GeneratedValue
	private long vrNumber;

	private long amountAdjustment;

	private long amountAnt;

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

	public long getVrNumber() {
		return vrNumber;
	}

	public void setVrNumber(long vrNumber) {
		this.vrNumber = vrNumber;
	}

	public long getAmountAdjustment() {
		return amountAdjustment;
	}

	public void setAmountAdjustment(long amountAdjustment) {
		this.amountAdjustment = amountAdjustment;
	}

	public long getAmountAnt() {
		return amountAnt;
	}

	public void setAmountAnt(long amountAnt) {
		this.amountAnt = amountAnt;
	}

}
