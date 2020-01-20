package com.xyz.searchcatalog.search;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xyz.searchcatalog.search.dao.SearchDAO;
import com.xyz.searchcatalog.search.productvo.Product;

@EnableJpaRepositories(basePackages = {"com.xyz.searchcatalog.search"})
@EntityScan(value = {"com.xyz.searchcatalog.search"})
@Service
public class SearchServiceImpl implements SearchService{
	
	
	public SearchServiceImpl() {
		super();
	}
	
	@Autowired
	SearchDAO searchDAO;
	
	Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
	
	@CacheEvict(allEntries = true, cacheNames = { "product" })
	@Scheduled(fixedDelay = 60*120)
	public void cacheEvict() {
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		System.out.println("Cache refreshed"+ formatter.format(new Date()));
	}

	/*
	 * This method returns search result for a specific sku
	 */
	@Transactional
	@Cacheable(value = "product", key="#sku") 
	public Product getProductBySku(String sku) {
		Product p =null;
		p= searchDAO.getProductBySku(sku);
		return p;
	}

	/*
	 * This method adds a product
	 */
	@Transactional
	@Modifying
	@Override
	@CachePut(value ="product")
	public void addProduct(Product product) {
		if(product!=null) {
			logger.debug("Adding a Product with sku:"+product.getSku());
			searchDAO.addProduct(product);
		}
	}
	
	/*
	 * This method deletes a product by sku
	 */
	@Transactional
	@Override
	@Caching(evict={@CacheEvict(value="product", key="#sku",allEntries = false)})
	public void deleteProduct(String sku) {
		searchDAO.deleteProduct(sku);
	}

	/*
	 * This method searches a product by its sku or color or brand or price or size
	 */
	@Override
	@Transactional
	@Cacheable(value = "product")
	public ArrayList<Product> searchProduct(String sku, String color, String brand, String price, String size) {
		Double p = null;
		if(price.length()>0) {
			try {
				p = Double.valueOf(price);
			}
			catch(ClassCastException e) {
				logger.error("Invalid price:"+price+"! Considering 0.00");
			}
		}

		return searchDAO.searchProduct(sku, color, brand, p, size);
	}

	/*
	 * This method retrives number of products by a seller
	 */
	@Override
	@Cacheable(value = "sellerStock")
	public Long getTotalProductsBySellerName(String sellerName) {
		return searchDAO.getTotalProductsBySellerName(sellerName);
	}
	
	/*
	 * Fetch all products
	 */
	@Transactional
	public ArrayList<Product> getAllProducts() {
		return searchDAO.getAllProduct();
	}

	@Transactional
	@Modifying
	@Override
	public String orderProduct(String sku){
		try {
			searchDAO.orderProduct(sku);
			cacheEvict();
			return "SUCCESS";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Some error has occured while ordering. Order could not be confirmed");
			return "FAILURE";
		}
		
	}
	
}
