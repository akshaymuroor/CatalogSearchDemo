package com.xyz.searchcatalog.search;

import java.util.ArrayList;

import javax.xml.ws.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.searchcatalog.search.productvo.Product;

@RestController
public class SearchController {

	@Autowired
	SearchService service;
	
	//Wrapper response
	@Autowired
	SearchResponse wrResponse;
	
	Logger logger = LoggerFactory.getLogger(SearchController.class);
		
	
	@RequestMapping("/AllProducts")
	public SearchResponse getAllProducts() {
		logger.info("Fetching all products...");
		wrResponse.setArrProducts(service.getAllProducts());
		return wrResponse;
	}
	
	@RequestMapping("/GetProduct/{sku}")
	public Product getProductBySku(@PathVariable String sku) {
		logger.info("Getting specific Product sku: "+ sku);
		return service.getProductBySku(sku);
	}
	
	@RequestMapping("/search")
	public ArrayList<Product> searchProduct(@RequestParam String sku, @RequestParam String brand,  
			@RequestParam String color, @RequestParam String price, @RequestParam String size) {
		logger.info("Getting Product based on sku/brand/color/price/size: "+ sku);
		return service.searchProduct(sku,color,brand,price,size);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/AddProduct")
	public void addProduct(@RequestBody Product product) {
		logger.info("Adding a product");
		service.addProduct(product);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/Delete/{sku}")
	public void deleteProduct(@PathVariable String sku) {
		logger.debug("Deleting a Product with sku: "+sku);
		service.deleteProduct(sku);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/ProductsBySeller/{sellerName}")
	public Long getTotalProductsBySellerName(@PathVariable String sellerName) {
		logger.debug("Fetching number of products by: "+sellerName);
		return service.getTotalProductsBySellerName(sellerName);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/Order/{sku}")
	@ResponseStatus(value = HttpStatus.OK)
	public String orderConfirmed(@PathVariable String sku) {
		logger.debug("Order confirmation for a product with sku "+sku);
		return service.orderProduct(sku);
	}

}
