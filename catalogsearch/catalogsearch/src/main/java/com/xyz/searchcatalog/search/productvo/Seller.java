package com.xyz.searchcatalog.search.productvo;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Index;

@javax.persistence.Entity(name="seller")
public class Seller {

	
	
	public Seller() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Seller(int sellerId, String sellerName, String address, Double rating, Date createdDatetime,
			Date modifiedDatetime) {
		super();
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.address = address;
		this.rating = rating;
		this.createdDatetime = createdDatetime;
		this.modifiedDatetime = modifiedDatetime;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sellerId;
	@Column(unique = true)
	@Index(name = "seller")
	private String sellerName;
	private String address;
	private Double rating;
	
	@Column(name="createdDatetime", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdDatetime;
	
	@Column(name="modifiedDatetime", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date modifiedDatetime;
	/**
	 * @return the seller_id
	 */
	public int getSellerId() {
		return sellerId;
	}


	/**
	 * @param sellerId the sellerId to set
	 */
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}


	/**
	 * @return the sellerName
	 */
	public String getSellerName() {
		return sellerName;
	}


	/**
	 * @param seller_name the seller_name to set
	 */
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}


	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @return the rating
	 */
	public Double getRating() {
		return rating;
	}


	/**
	 * @param rating the rating to set
	 */
	public void setRating(Double rating) {
		this.rating = rating;
	}


	/**
	 * @return the createdDatetime
	 */
	public Date getCreatedDatetime() {
		return createdDatetime;
	}


	/**
	 * @param createdDatetime the createdDatetime to set
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
	 * @param modifiedDatetime the modifiedDatetime to set
	 */
	public void setModifiedDatetime(Date modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	
}
