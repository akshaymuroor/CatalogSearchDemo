package com.xyz.searchcatalog.search.productvo;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Index;

@javax.persistence.Entity(name ="product_spec")
@Embeddable
public class ProductSpec {

	public ProductSpec() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ProductSpec(int sprecId, String sku, String attribute, String value) {
		super();
		this.sprecId = sprecId;
		this.sku = sku;
		this.attribute = attribute;
		this.value = value;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int sprecId;
	@Index(name = "sku_id")
	private String sku;
	@Index(name = "attribute")
	private String attribute;
	private String value;
	/**
	 * @return the sprecId
	 */
	public int getSprecId() {
		return sprecId;
	}
	/**
	 * @param sprecId the sprecId to set
	 */
	public void setSprec_id(int sprecId) {
		this.sprecId = sprecId;
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
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}
	/**
	 * @param attribute the attribute to set
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
