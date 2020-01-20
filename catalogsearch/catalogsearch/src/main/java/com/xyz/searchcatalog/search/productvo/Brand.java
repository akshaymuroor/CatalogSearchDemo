package com.xyz.searchcatalog.search.productvo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Index;

@javax.persistence.Entity(name ="brand")
public class Brand {

	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Brand(int brandId, String brandName, String rating) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
		this.rating = rating;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int brandId;
	@Column(unique = true)
	@Index(name = "brand")
	private String brandName;
	private String rating;
	/**
	 * @return the brandId
	 */
	public int getBrandId() {
		return brandId;
	}
	/**
	 * @param brand_id the brandId to set
	 */
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * @param brand_name the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	
}
