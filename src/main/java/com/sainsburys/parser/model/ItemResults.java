package com.sainsburys.parser.model;

import java.util.List;

public class ItemResults {

	private List<ItemDetails> results;
	
	private double total;
	
	public ItemResults(List<ItemDetails> results, double total){
		this.results = results;
		this.total = total;
	}

	public List<ItemDetails> getResults() {
		return results;
	}



	public void setResults(List<ItemDetails> results) {
		this.results = results;
	}



	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
}
