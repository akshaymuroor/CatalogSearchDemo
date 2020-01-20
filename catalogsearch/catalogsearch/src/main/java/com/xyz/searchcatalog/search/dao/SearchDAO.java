package com.xyz.searchcatalog.search.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.xyz.searchcatalog.search.productvo.Product;
import com.xyz.searchcatalog.search.productvo.ProductSpec;
import com.xyz.searchcatalog.search.repo.BrandRepository;
import com.xyz.searchcatalog.search.repo.CategoryRepository;
import com.xyz.searchcatalog.search.repo.ProductRepository;
import com.xyz.searchcatalog.search.repo.ProductSpecRepository;
import com.xyz.searchcatalog.search.repo.SellerRepository;

@Repository
public class SearchDAO {
	
	Logger logger = LoggerFactory.getLogger(SearchDAO.class);
	
	@Autowired
	ProductRepository productRepo ;
	@Autowired
	BrandRepository brandRepo;
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	ProductSpecRepository productSpecRep;
	@Autowired
	SellerRepository sellerRepo;
	

	public SearchDAO() {
		super();
		loadProducts();
	}
	
	public ArrayList<Product> getAllProduct() {
		System.out.println("Hitting DAO!");	
		ArrayList<Product> pl = (ArrayList<Product>) productRepo.findAll();
		if(pl!=null) {
			for (Product product : pl) {
				product.setSpec((List<ProductSpec>) productSpecRep.findAllBySku(product.getSku()));
			}
		}
		return pl;
	}
	
	public Long getTotalProductsBySellerName(String sellername) {
		return productRepo.countBySellerName(sellername);
	}

	public Product getProductBySku(String sku) {
		System.out.println("htting Dao");
		//return arrlProduct.stream().filter(t->t.getSku().equals(sku)).findFirst().get();
		Product p =productRepo.findBySku(sku);
		if(p!=null) {
			p.setSpec((List<ProductSpec>) productSpecRep.findAllBySku(p.getSku()));
		}
		return p;
	}

	@Modifying
	public void addProduct(Product product) {
		productRepo.save(product);
		
		List<ProductSpec> ps = product.getSpec();
		for (ProductSpec productSpec : ps) {
			productSpecRep.save(productSpec);
		}
		
	}

	@Modifying
	public void deleteProduct(String sku) {
		//arrlProduct.removeIf(t->t.getSku().equals(sku));
		productRepo.deleteBySku(sku);
	}


	public ArrayList<Product> searchProduct(String sku, String color, String brand, Double price, String size) {
		Connection conn=null;
		Statement st=null;
		try {
			Class.forName ("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		logger.debug("Inside DAO...");
		
		ArrayList<Product> arrResult= new ArrayList<Product>();
		try {
			
			logger.debug("connection open in progress");
			conn=DriverManager.getConnection ("jdbc:h2:mem:testdb", "sa","");
			st = conn.createStatement();
			logger.debug("Statement creation");
			st.execute("SELECT DISTINCT PRODUCT_ID,P.SKU,NAME,CATEGORY,BRAND,DESCRIPTION,SELLER_NAME,STOCK,PRICE,CREATED_DATETIME,MODIFIED_DATETIME"
					+ " FROM PRODUCT P LEFT OUTER JOIN PRODUCT_SPEC PS ON P.SKU=PS.SKU \r\n" + 
					"WHERE P.SKU='"+(sku!=null?sku:"")+"'  \r\n" + 
					"OR BRAND='"+(brand!=null?brand:"")+"' \r\n" + 
					"OR PRICE='"+(price!=null?price:0)+"' \r\n" + 
					"OR (PS.ATTRIBUTE='color' AND PS.VALUE='"+(color!=null?color:"")+"')"+
					"OR (PS.ATTRIBUTE='size' AND PS.VALUE='"+(size!=null?size:"")+"')"
					+ ";");  
			
			logger.debug("Staement execution completed");
			ResultSet rs =  st.getResultSet();
			
			while(rs.next())
			{
				Product p = new Product();
				p.setProductId(rs.getLong("PRODUCT_ID"));
				p.setBrand(rs.getString("BRAND"));
				p.setCategory(rs.getString("CATEGORY"));
				p.setSku(rs.getString("SKU"));
				p.setName(rs.getString("NAME"));
				
				p.setDescription(rs.getString("DESCRIPTION"));
				p.setSellerName(rs.getString("SELLER_NAME"));
				p.setStock(rs.getInt("STOCK"));
				p.setPrice(rs.getDouble("PRICE"));
				p.setCreatedDatetime(rs.getDate("CREATED_DATETIME"));
				p.setModifiedDatetime(rs.getDate("MODIFIED_DATETIME"));
				
				List<ProductSpec> ps=  (List<ProductSpec>) productSpecRep.findAllBySku(p.getSku());
				p.setSpec(ps);
				arrResult.add(p);
			}
			
			logger.debug("Printing the resuklt set");
			arrResult.stream().forEach(System.out::print);
			
			
			
			logger.debug("Closing connection in try block");
			conn.close();
			
		} catch (SQLException e) {
			logger.error("SQL Exception thrown while executing search.\n"+ e.getMessage());
			e.printStackTrace();
			try {
				if(conn!=null) conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}
		finally {
			try {
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return arrResult;
		
	}
	
	public void loadProducts() {
		Connection con=null;
		Statement st=null;		
		try {
			Class.forName ("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con=DriverManager.getConnection ("jdbc:h2:mem:testdb", "sa","");
			st = con.createStatement();
			
			st.execute("INSERT INTO BRAND ( BRAND_ID , BRAND_NAME , RATING ) VALUES (1,	'Asics', 	4.5),"+
					"(2,	'Nike',	4.3),"+
					"(3,	'Tomy',	4.7),"+
					"(4,	'Apple',	4.8),"+
					"(5,	'Reebok',	4.1);");
			st.execute("INSERT INTO CATEGORY ( CATEGORY_ID , CATEGORY_NAME , DESCRIPTION ) \r\n" + 
					"VALUES (1,	'Shoe',	'Put on to walk, jog, run. Has many varieties'),\r\n" + 
					"(2,	'Pant',	'Comfort and stylish'),\r\n" + 
					"(3,	'Cap',	'Protects from sunstroke'),\r\n" + 
					"(4,	'Mobile',	'Electronics'),\r\n" + 
					"(5,	'Shirt',	'Wearable with half sleeve and full sleeve options');");
			st.execute("INSERT INTO SELLER(SELLER_ID	,SELLER_NAME,	ADDRESS	,RATING,	CREATED_DATETIME	,MODIFIED_DATETIME)" + 
					"VALUES\r\n" + 
					"(1,	'Magic co. Ltd.',	'Bangalore',	4.5,	'2020-01-17',		'2020-01-17')," + 
					"(2,	'Satva pvt ltd.',	'Chennai',	4.2,		'2020-01-17',		'2020-01-17')," + 
					"(3,	'Sangeetha Ltd',	'Delhi',	4.8,		'2020-01-17',		'2020-01-17');");
			st.execute("INSERT INTO PRODUCT(PRODUCT_ID,SKU,NAME,CATEGORY,BRAND,DESCRIPTION,SELLER_NAME,STOCK,PRICE,CREATED_DATETIME,MODIFIED_DATETIME) "
					+ "VALUES(1, 'abcde123',	'Asics gel Kayana 25',	    'Shoe',	'Asics', 'Running shoe',	'Magic co. Ltd.',	12,	8999.00,	'2020-01-17',	'2020-01-17')" + 
					",(2, 'bbcde133',	'XYZ Slim fit casual pant',	'Pant',	'Nike',	 'Casual jeans',	'Magic co. Ltd.',	4,	5499.00,	'2020-01-17',	'2020-01-17')" + 
					",(3, 'dsdsf345',	'ABC roudn cap',	        'Cap',	'Tomy',	 'Round cap', 	    'Satva pvt ltd.',	6,	700.00,	'2020-01-17',	'2020-01-17')" + 
					",(4, 'ertyu678',	'iPhone pro max',	        'Mobile','Apple', 'Smart phone',	'Sangeetha Ltd',	87,	89000.00,	'2020-01-17',	'2020-01-17')" + 
					",(5, 'sdfg1254',	'Process Black contrast piping slim fit shirt',	'Shirt',	'Reebok',	'Full sleeve', 	'Satva pvt ltd.',	10,	2345.00,	'2020-01-17',	'2020-01-17');");
			st.execute("INSERT INTO PRODUCT_SPEC  ( SPREC_ID , SKU , ATTRIBUTE , VALUE ) VALUES (1,'abcde123', 'color','white')\r\n" + 
					",(2,'abcde123', 'size','29.5')\r\n" + 
					",(3,'bbcde133', 'size','L')\r\n" + 
					",(4,'dsdsf345', 'color','blue')\r\n" + 
					",(5,'ertyu678', 'color','rose gold')\r\n" + 
					",(6,'sdfg1254', 'color','black');");
			con.close();
			
		} catch (SQLException e) {
			logger.error("SQL Exception thrown while executing loadProducts.\n"+ e.getMessage());
			e.printStackTrace();
			try {
				if(con!=null) con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		finally {
			try {
				if(con!=null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Modifying
	public void orderProduct(String sku) throws Exception{
		int stock =productRepo.productStock(sku); 
		if(stock==0) {
			throw new Exception("Wrong SKU or No stock");
		}
		else {
			logger.info("Stock available: "+stock);
			productRepo.updateCountOfProduct(sku);
		}
		
	}
	
}
