package com.xyz.searchcatalog.search.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.xyz.searchcatalog.search.productvo.ProductSpec;

public interface ProductSpecRepository extends CrudRepository<ProductSpec, Integer>{

	List<ProductSpec> findAllBySku(String sku);

}
