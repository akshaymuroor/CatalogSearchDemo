package com.xyz.searchcatalog.search;

import java.util.ArrayList;
import com.xyz.searchcatalog.search.productvo.Product;

public interface SearchService {

	public ArrayList<Product> getAllProducts();
	public Product getProductBySku(String sku);
	public void addProduct(Product product);
	public void deleteProduct(String sku);
	public ArrayList<Product> searchProduct(String sku, String color, String brand, String price, String size);
	public Long getTotalProductsBySellerName(String sellerName);
	public String orderProduct(String sku);
}
