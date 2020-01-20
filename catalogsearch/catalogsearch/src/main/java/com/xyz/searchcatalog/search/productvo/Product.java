package com.xyz.searchcatalog.search.productvo;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;


@javax.persistence.Entity(name = "product")
public class Product {
	
	public Product() {
		super();
	}
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long productId;
	
	@Column(unique = true)
	@Index(name = "sku")
	private String sku;
	@Index(name = "productName")
	private String name;
	@Index(name = "categoryName")
	private String category;
	@Index(name = "brandName")
	private String brand;
	private String description;
	private String sellerName;
	private int stock;
	private Double price;
	
	@Basic(optional = true)
    @Column(name="createdDatetime", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdDatetime;
	
	@Basic(optional = true)
	@Column(name="modifiedDatetime", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date modifiedDatetime;
	
	@Transient
	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,
			 CascadeType.DETACH,CascadeType.REFRESH})
	private List<ProductSpec> spec;
	


	public Product(Long productId, String sku, String name, String category, String brand, String description,
			String sellerName, int stock, Double price, Date createdDatetime, Date modifiedDatetime,
			ArrayList<ProductSpec> spec) {
		super();
		this.productId = productId;
		this.sku = sku;
		this.name = name;
		this.category = category;
		this.brand = brand;
		this.description = description;
		this.sellerName = sellerName;
		this.stock = stock;
		this.price = price;
		this.createdDatetime = createdDatetime;
		this.modifiedDatetime = modifiedDatetime;
		this.spec = spec;
	}

	public Product(Long productId, String sku, String name, String category, String brand, String description,
			String sellerName, int stock, Double price) {
		super();
		this.productId = productId;
		this.sku = sku;
		this.name = name;
		this.category = category;
		this.brand = brand;
		this.description = description;
		this.sellerName = sellerName;
		this.stock = stock;
		this.price = price;
		this.createdDatetime = new Date(119,8,18);
		this.modifiedDatetime = new Date(119,2,19);
	}
	
	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param product_id the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the sellerName
	 */
	public String getSellerName() {
		return sellerName;
	}

	/**
	 * @param seller_name the sellerName to set
	 */
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the createdDatetime
	 */
	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	/**
	 * @param created_datetime the createdDatetime to set
	 */
	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	/**
	 * @return the modifiedDatetime
	 */
	public Date getModifiedDatetime() {
		return modifiedDatetime;
	}

	/**
	 * @param modified_datetime the modifiedDatetime to set
	 */
	public void setModifiedDatetime(Date modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	/**
	 * @return the spec
	 */
	public List<ProductSpec> getSpec() {
		return spec;
	}

	/**
	 * @param spec the spec to set
	 */
	public void setSpec(List<ProductSpec> spec) {
		this.spec = spec;
	}

	
	
}
