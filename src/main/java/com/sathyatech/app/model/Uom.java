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
@Table(name="uom_tbl")
@EntityListeners(AuditingEntityListener.class)
public class Uom {
	@Id
	@Column(name="uom_id")
	@GeneratedValue(generator="uom_id_gen")
	@GenericGenerator(name="uom_id_gen",strategy="increment")
	private Long uomId;
	
	@Column(name="uom_type")
	private String uomType;
	
	@Column(name="uom_model")
	private String uomModel;
	
	@Column(name="uom_desc")
	private String description;
	
	@Column(name="c_dte")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@CreatedDate
	private Date createdOn;
	
	@Column(name="m_dte")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@LastModifiedDate
	private Date modifiedOn;

	public Uom() {
		super();
	}

	public Uom(Long uomId) {
		super();
		this.uomId = uomId;
	}

	public Uom(String uomType, String uomModel, String description) {
		super();
		this.uomType = uomType;
		this.uomModel = uomModel;
		this.description = description;
	}

	/*public Uom(Long uomId, String uomType, String uomModel, String description,
			Date createdOn, Date modifiedOn) {
		super();
		this.uomId = uomId;
		this.uomType = uomType;
		this.uomModel = uomModel;
		this.description = description;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
	}*/

	public Long getUomId() {
		return uomId;
	}

	public void setUomId(Long uomId) {
		this.uomId = uomId;
	}

	public String getUomType() {
		return uomType;
	}

	public void setUomType(String uomType) {
		this.uomType = uomType;
	}

	public String getUomModel() {
		return uomModel;
	}

	public void setUomModel(String uomModel) {
		this.uomModel = uomModel;
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
		return "Uom [uomId=" + uomId + ", uomType=" + uomType + ", uomModel="
				+ uomModel + ", description=" + description + ", createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn + "]";
	}
}
