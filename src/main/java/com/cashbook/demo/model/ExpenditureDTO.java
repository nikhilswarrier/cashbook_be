package com.cashbook.demo.model;

import java.sql.Date;

public class ExpenditureDTO {
     private int id;

	private Date date;

	private String particulars;
    
	private int vrNumber;

    private float amount;
    
    private float openingBalance;

    private float totalAmount;

    private float cumulativeBalance;

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

    public void setId(int id) {
        this.id = id;
    }

    public float getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(float openingBalance) {
        this.openingBalance = openingBalance;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getCumulativeBalance() {
        return cumulativeBalance;
    }

    public void setCumulativeBalance(float cumulativeBalance) {
        this.cumulativeBalance = cumulativeBalance;
    }
}