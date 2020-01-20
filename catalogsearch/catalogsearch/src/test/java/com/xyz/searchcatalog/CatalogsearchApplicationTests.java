package com.xyz.searchcatalog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.xyz.searchcatalog.search.SearchService;
import com.xyz.searchcatalog.search.productvo.Product;

@SpringBootTest
@RunWith(SpringRunner.class)
class CatalogsearchApplicationTests {

	@Autowired
	private SearchService service;
	
	Logger logger = LoggerFactory.getLogger(CatalogsearchApplicationTests.class);

	
	@Test
	void getProductBySku() {
		logger.info("Test case 1: Running getProductBySku test case");
		Product p =service.getProductBySku("abcde123");
		assertThat("Asics gel Kayana 25").isEqualTo(p.getName());
	}
	
	@Test
	void getAllProducts() {
		logger.info("Test case 2: Running getAllProducts test case");
		ArrayList<Product> pl = service.getAllProducts();
		assertTrue(pl !=null);
		assertTrue(pl.stream().count()>=1);
	}

	@Test
	void getTotalProductsBySellerName() {
		logger.info("Test case 3: getTotalProductBySellerName");
		assertTrue(service.getTotalProductsBySellerName("Magic co. Ltd.")>=0);
	}
	
	@Test
	void searchProduct() {
		logger.info("Test case 4: searchProduct");
		ArrayList<Product> pl = service.searchProduct("", "white", "Asics", "8999", "L");
		assertTrue(pl!=null);
		assertTrue(pl.stream().count()>=0);
	}
	
	

	/*  ------------Sample data for testing -----------*/
	/*
	 * ArrayList<ProductSpec> arrShoeSpec = new ArrayList<>( Arrays.asList(new
	 * ProductSpec(1,"abcde123", "color","white") , new ProductSpec(2,"abcde123",
	 * "size","29.5")));
	 * 
	 * ArrayList<ProductSpec> arrPantSpec = new ArrayList<>( Arrays.asList(new
	 * ProductSpec(3,"bbcde133", "size","L") ));
	 * 
	 * ArrayList<ProductSpec> arrCapSpec = new ArrayList<>(Arrays.asList(new
	 * ProductSpec(4,"dsdsf345", "color","blue") )); ArrayList<ProductSpec>
	 * arrMobileSpec = new ArrayList<>(Arrays.asList(new ProductSpec(5,"ertyu678",
	 * "color","rose gold"))); ArrayList<ProductSpec> arrShirtSpec = new
	 * ArrayList<>(Arrays.asList(new ProductSpec(6,"sdfg1254", "color","black")));
	 * ArrayList<Product> arrlProduct= new ArrayList<>( Arrays.asList( // new
	 * Product(1, "abcde123", "Asics gel Kayana 25", "Shoe", "Asics",
	 * "Running shoe", "Magic co. Ltd.", 12, 8999.00, new Date(), new Date(),
	 * arrShoeSpec) // ,new Product(2, "bbcde133", "XYZ Slim fit casual pant",
	 * "Pant", "Nike", "Casual jeans", "Magic co. Ltd.", 4, 5499.00, new Date(), new
	 * Date(), arrPantSpec) // ,new Product(3, "dsdsf345", "ABC roudn cap", "Cap",
	 * "Tomy", "Round cap", "Satva pvt ltd.", 6, 700.00, new Date(), new
	 * Date(),arrCapSpec) // ,new Product(4, "ertyu678", "iPhone pro max",
	 * "Mobile","Apple", "Smart phone", "Sangeetha Ltd", 87, 89000.00, new Date(),
	 * new Date(),arrMobileSpec) // ,new Product(5, "sdfg1254",
	 * "Process Black contrast piping slim fit shirt", "Shirt", "Reebok",
	 * "Full sleeve", "Satva pvt ltd.", 10, 2345.00, new Date(), new
	 * Date(),arrShirtSpec) ) );
	 */
	/*  -----------------------*/
}
