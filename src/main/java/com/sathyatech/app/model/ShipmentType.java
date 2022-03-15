package com.sathyatech.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="ship_type_tbl")
@EntityListeners(AuditingEntityListener.class)
public class ShipmentType {
	@Id
	@Column(name="ship_id",length=20)
	@GeneratedValue(generator="ship_id_gen")
	@GenericGenerator(name="ship_id_gen",strategy="increment")
	private Long shipmentId;
	
	@Column(name="ship_mode")
	private String shipmentMode;
	
	@Column(name="ship_code")
	private String shipmentCode;
	
	@Column(name="ship_enable")
	private String enableShipment;
	
	@Column(name="ship_grade")
	private String shipmentGrade;
	
	@Column(name="ship_desc")
	private String description;
	
	@Column(name="ship_s_dte")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@CreatedDate
	private Date createdOn;
	
	@Column(name="ship_m_dte")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@LastModifiedDate
	private Date modifiedOn;

	public ShipmentType() {
		super();
	}

	public ShipmentType(Long shipmentId) {
		super();
		this.shipmentId = shipmentId;
	}
	
	
	
	public ShipmentType(String shipmentMode, String enableShipment,
			String shipmentGrade, String description) {
		super();
		this.shipmentMode = shipmentMode;
		this.enableShipment = enableShipment;
		this.shipmentGrade = shipmentGrade;
		this.description = description;
	}

	public ShipmentType(Long shipmentId, String shipmentMode,
			String shipmentCode, String enableShipment, String shipmentGrade,
			String description, Date createdOn, Date modifiedOn) {
		super();
		this.shipmentId = shipmentId;
		this.shipmentMode = shipmentMode;
		this.shipmentCode = shipmentCode;
		this.enableShipment = enableShipment;
		this.shipmentGrade = shipmentGrade;
		this.description = description;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
	}

	public Long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}

	public String getShipmentMode() {
		return shipmentMode;
	}

	public void setShipmentMode(String shipmentMode) {
		this.shipmentMode = shipmentMode;
	}

	public String getShipmentCode() {
		return shipmentCode;
	}

	public void setShipmentCode(String shipmentCode) {
		this.shipmentCode = shipmentCode;
	}

	public String getEnableShipment() {
		return enableShipment;
	}

	public void setEnableShipment(String enableShipment) {
		this.enableShipment = enableShipment;
	}

	public String getShipmentGrade() {
		return shipmentGrade;
	}

	public void setShipmentGrade(String shipmentGrade) {
		this.shipmentGrade = shipmentGrade;
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
		return "ShipmentType [shipmentId=" + shipmentId + ", shipmentMode="
				+ shipmentMode + ", shipmentCode=" + shipmentCode
				+ ", enableShipment=" + enableShipment + ", shipmentGrade="
				+ shipmentGrade + ", description=" + description
				+ ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn
				+ "]";
	}
}
