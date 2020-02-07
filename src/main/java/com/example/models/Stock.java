package com.example.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {
	private @Id @GeneratedValue Long id;
	private String name;
	
	@Column(name="current_Price")
	private String currentPrice;
	
	@Column(name="last_Update")
	private Date lastUpdate;

	public Stock() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastUpdate() {
		SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return lastUpdate == null ? "" : simple.format(lastUpdate);
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	
	public void setCurrentPrice(String currentPrice) {
	    this.currentPrice = currentPrice;
	}

	public String getCurrentPrice() {
		return currentPrice == null ? "" : currentPrice;
	}
}
