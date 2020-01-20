package com.xyz.searchcatalog.search;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.xyz.searchcatalog.search.productvo.Product;

@Component
public class SearchResponse {
	ArrayList<Product> arrProducts;

	/**
	 * @return the arrProducts
	 */
	public ArrayList<Product> getArrProducts() {
		return arrProducts;
	}

	/**
	 * @param arrProducts the arrProducts to set
	 */
	public void setArrProducts(ArrayList<Product> arrProducts) {
		this.arrProducts = arrProducts;
	}
	
}
