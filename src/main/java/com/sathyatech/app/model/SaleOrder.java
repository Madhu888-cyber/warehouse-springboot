package com.sathyatech.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="sale_ord")
@EntityListeners(AuditingEntityListener.class)
public class SaleOrder {
	
	@Id
	@Column(name="sale_id")
	@GeneratedValue(generator="id_gen")
	@GenericGenerator(name="id_gen",strategy="increment")
	private Long saleId;
	
	@Column(name="sale_code")
	private String orderCode;
	
	@ManyToOne
	@JoinColumn(name="sale_shipId_fk")
	private ShipmentType saleShipmentMode = new ShipmentType();
	
	@ManyToOne
	@JoinColumn(name="sale_whUserId_fk")
	private WHUserType saleCustomers =new WHUserType();
	
	@Column(name="sale_refnum")
	private String referenceNumber;
	
	@Column(name="sale_stockmode")
	private String stockMode;
	
	@Column(name="sale_stocksource")
	private String stockSource;
	
	@Column(name="sale_status")
	private String status;
	
	@Column(name="sale_desc")
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="sale_id")
	private List<SaleOrderDetails> saleDetails = new ArrayList<SaleOrderDetails>(0);
	
	@Column(name="ct_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@CreatedDate
	private Date createdOn;
	
	@Column(name="md_date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@LastModifiedDate
	private Date modifiedOn;

	public SaleOrder() {
		super();
	}

	public SaleOrder(String status) {
		super();
		this.status = status;
	}

	

	public SaleOrder(String orderCode, ShipmentType saleShipmentMode,
			WHUserType saleCustomers, String referenceNumber, String stockMode,
			String stockSource, String description) {
		super();
		this.orderCode = orderCode;
		this.saleShipmentMode = saleShipmentMode;
		this.saleCustomers = saleCustomers;
		this.referenceNumber = referenceNumber;
		this.stockMode = stockMode;
		this.stockSource = stockSource;
		this.description = description;
	}

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public ShipmentType getSaleShipmentMode() {
		return saleShipmentMode;
	}

	public void setSaleShipmentMode(ShipmentType saleShipmentMode) {
		this.saleShipmentMode = saleShipmentMode;
	}

	public WHUserType getSaleCustomers() {
		return saleCustomers;
	}

	public void setSaleCustomers(WHUserType saleCustomers) {
		this.saleCustomers = saleCustomers;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getStockMode() {
		return stockMode;
	}

	public void setStockMode(String stockMode) {
		this.stockMode = stockMode;
	}

	public String getStockSource() {
		return stockSource;
	}

	public void setStockSource(String stockSource) {
		this.stockSource = stockSource;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SaleOrderDetails> getSaleDetails() {
		return saleDetails;
	}

	public void setSaleDetails(List<SaleOrderDetails> saleDetails) {
		this.saleDetails = saleDetails;
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
		return "SaleOrder [saleId=" + saleId + ", orderCode=" + orderCode
				+ ", saleShipmentMode=" + saleShipmentMode + ", saleCustomers="
				+ saleCustomers + ", referenceNumber=" + referenceNumber
				+ ", stockMode=" + stockMode + ", stockSource=" + stockSource
				+ ", status=" + status + ", description=" + description
				+ ", saleDetails=" + saleDetails + ", createdOn=" + createdOn
				+ ", modifiedOn=" + modifiedOn + "]";
	}
}
