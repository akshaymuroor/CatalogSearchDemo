package com.xyz.searchcatalog.search.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xyz.searchcatalog.search.productvo.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

	Product findBySku(String sku);

	void deleteBySku(String sku);

	//@Query("SELECT COUNT(1) FROM product p WHERE p.sellername=:seller_name")
	Long countBySellerName(String sellername);

	@Query("SELECT stock FROM product p WHERE p.sku=:sku")
	int productStock(@Param("sku") String sku);

	@Modifying
	@Query("UPDATE product p set p.stock=p.stock-1 where p.sku=:sku")
	void updateCountOfProduct(@Param("sku") String sku);

}
