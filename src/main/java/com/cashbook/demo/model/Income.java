/**
 * 
 */
package com.cashbook.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Nikhil S Warrier 
 * Table for Income part of 
 * cashbook.
 *
 */
@Entity
@Table(name = "income")
public class Income {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date date;

	private String particulars;
	
	private long vrNumber;

	private long amount;

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

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}


}
