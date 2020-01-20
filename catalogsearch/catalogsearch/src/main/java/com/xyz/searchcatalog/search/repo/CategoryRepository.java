package com.xyz.searchcatalog.search.repo;

import org.springframework.data.repository.CrudRepository;

import com.xyz.searchcatalog.search.productvo.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>{

}
