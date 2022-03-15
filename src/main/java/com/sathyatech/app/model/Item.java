package com.sathyatech.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="item_tab")
@EntityListeners(AuditingEntityListener.class)
public class Item {
	
	@Id
	@Column(name="itm_id")
	@GeneratedValue(generator="item_id_gen")
	@GenericGenerator(name="item_id_gen",strategy="increment")
	private Long itemId;
	
	@Column(name="itm_code")
	private String itemCode;
	
	@Column(name="itm_width")
	private Double itemWidth;
	
	@Column(name="itm_length")
	private Double itemLength;
	
	@Column(name="itm_height")
	private Double itemHeight;
	
	@Column(name="itm_base_cost")
	private Double itemBaseCost;
	
	@Column(name="itm_currency")
	private String itemBaseCurrency;
	
	@ManyToOne
	@JoinColumn(name="uomId_fk",referencedColumnName="uom_id")
	private Uom uom = new Uom();
	
	@ManyToOne
	@JoinColumn(name="ordId_sale_fk",referencedColumnName="ord_id")
	private OrderMethod orderSale = new OrderMethod();
	
	@ManyToOne
	@JoinColumn(name="ordId_pur_fk",referencedColumnName="ord_id")
	private OrderMethod orderPurchase = new OrderMethod();
	
	@ManyToMany
	@JoinTable(name="item_whuser_ven",joinColumns=@JoinColumn(name="itm_ven_fk"),
			   inverseJoinColumns=@JoinColumn(name="whuse_ven_fk"))
	private List<WHUserType> vendors = new ArrayList<WHUserType>();
	
	@ManyToMany
	@JoinTable(name="item_whuser_cust",joinColumns=@JoinColumn(name="itm_cust_fk"),
			   inverseJoinColumns=@JoinColumn(name="whuse_cust_fk"))
	private List<WHUserType> customers = new ArrayList<WHUserType>();
	
	@Column(name="item_desc")
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@CreatedDate
	@Column(name="createdOn")
	private Date createdOn;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@LastModifiedDate
	@Column(name="modifiedOn")
	private Date modifiedOn;
	
	//Default Constructor
	public Item() {
		super();
	}
	
	//PK Based Constructor
	public Item(Long itemId) {
		super();
		this.itemId = itemId;
	}
	
	//Constructor For Multipart
	public Item(String itemCode, Double itemWidth, Double itemLength, Double itemHeight, Double itemBaseCost,
			String itemBaseCurrency, Uom uom, OrderMethod orderSale, OrderMethod orderPurchase,
			List<WHUserType> vendors, List<WHUserType> customers, String description) {
		super();
		this.itemCode = itemCode;
		this.itemWidth = itemWidth;
		this.itemLength = itemLength;
		this.itemHeight = itemHeight;
		this.itemBaseCost = itemBaseCost;
		this.itemBaseCurrency = itemBaseCurrency;
		this.uom = uom;
		this.orderSale = orderSale;
		this.orderPurchase = orderPurchase;
		this.vendors = vendors;
		this.customers = customers;
		this.description = description;
	}
	
	//
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Double getItemWidth() {
		return itemWidth;
	}

	public void setItemWidth(Double itemWidth) {
		this.itemWidth = itemWidth;
	}

	public Double getItemLength() {
		return itemLength;
	}

	public void setItemLength(Double itemLength) {
		this.itemLength = itemLength;
	}

	public Double getItemHeight() {
		return itemHeight;
	}

	public void setItemHeight(Double itemHeight) {
		this.itemHeight = itemHeight;
	}

	public Double getItemBaseCost() {
		return itemBaseCost;
	}

	public void setItemBaseCost(Double itemBaseCost) {
		this.itemBaseCost = itemBaseCost;
	}

	public String getItemBaseCurrency() {
		return itemBaseCurrency;
	}

	public void setItemBaseCurrency(String itemBaseCurrency) {
		this.itemBaseCurrency = itemBaseCurrency;
	}

	public Uom getUom() {
		return uom;
	}

	public void setUom(Uom uom) {
		this.uom = uom;
	}

	public OrderMethod getOrderSale() {
		return orderSale;
	}

	public void setOrderSale(OrderMethod orderSale) {
		this.orderSale = orderSale;
	}

	public OrderMethod getOrderPurchase() {
		return orderPurchase;
	}

	public void setOrderPurchase(OrderMethod orderPurchase) {
		this.orderPurchase = orderPurchase;
	}

	public List<WHUserType> getVendors() {
		return vendors;
	}

	public void setVendors(List<WHUserType> vendors) {
		this.vendors = vendors;
	}

	public List<WHUserType> getCustomers() {
		return customers;
	}

	public void setCustomers(List<WHUserType> customers) {
		this.customers = customers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemCode=" + itemCode + ", itemWidth=" + itemWidth + ", itemLength="
				+ itemLength + ", itemHeight=" + itemHeight + ", itemBaseCost=" + itemBaseCost + ", itemBaseCurrency="
				+ itemBaseCurrency + ", uom=" + uom + ", orderSale=" + orderSale + ", orderPurchase=" + orderPurchase
				+ ", vendors=" + vendors + ", customers=" + customers + ", description=" + description + ", createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn + "]";
	}	
}
