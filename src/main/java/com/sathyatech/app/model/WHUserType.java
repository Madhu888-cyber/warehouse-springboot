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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="whuser_tab")
@EntityListeners(AuditingEntityListener.class)
public class WHUserType {
	@Id
	@Column
	@GeneratedValue(generator="user_id_gen")
	@GenericGenerator(name="user_id_gen", strategy="increment")
	private Long whUserId;
	
	@Column
	private String userType;
	
	@Column
	@JsonIgnore
	private String whUserCode;
	
	@Column
	private String whUserFor;
	
	@Column
	private String whUserEmail;
	
	@Column
	private String whUserContact;
	
	@Column
	private String whUserIdType;
	
	@Column
	private String whIfOther;
	
	@Column
	private String whUserIdNumber;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@CreatedDate
	private Date createdOn;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@LastModifiedDate
	private Date modeifiedOn;
	
	//Default Constructor
	public WHUserType() {
		super();
	}
	
	//PK Based Constructor
	public WHUserType(Long whUserId) {
		super();
		this.whUserId = whUserId;
	}
	
	//Constructor For Multipart
	public WHUserType(String userType, String whUserCode, String whUserFor,
			String whUserEmail, String whUserContact, String whUserIdType,
			String whIfOther, String whUserIdNumber) {
		super();
		this.userType = userType;
		this.whUserCode = whUserCode;
		this.whUserFor = whUserFor;
		this.whUserEmail = whUserEmail;
		this.whUserContact = whUserContact;
		this.whUserIdType = whUserIdType;
		this.whIfOther = whIfOther;
		this.whUserIdNumber = whUserIdNumber;
	}

	public Long getWhUserId() {
		return whUserId;
	}


	public void setWhUserId(Long whUserId) {
		this.whUserId = whUserId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getWhUserCode() {
		return whUserCode;
	}

	public void setWhUserCode(String whUserCode) {
		this.whUserCode = whUserCode;
	}

	public String getWhUserFor() {
		return whUserFor;
	}

	public void setWhUserFor(String whUserFor) {
		this.whUserFor = whUserFor;
	}

	public String getWhUserEmail() {
		return whUserEmail;
	}

	public void setWhUserEmail(String whUserEmail) {
		this.whUserEmail = whUserEmail;
	}

	public String getWhUserContact() {
		return whUserContact;
	}

	public void setWhUserContact(String whUserContact) {
		this.whUserContact = whUserContact;
	}

	public String getWhUserIdType() {
		return whUserIdType;
	}

	public void setWhUserIdType(String whUserIdType) {
		this.whUserIdType = whUserIdType;
	}

	public String getWhIfOther() {
		return whIfOther;
	}

	public void setWhIfOther(String whIfOther) {
		this.whIfOther = whIfOther;
	}

	public String getWhUserIdNumber() {
		return whUserIdNumber;
	}

	public void setWhUserIdNumber(String whUserIdNumber) {
		this.whUserIdNumber = whUserIdNumber;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModeifiedOn() {
		return modeifiedOn;
	}

	public void setModeifiedOn(Date modeifiedOn) {
		this.modeifiedOn = modeifiedOn;
	}

	@Override
	public String toString() {
		return "WHUserType [whUserId=" + whUserId + ", userType=" + userType
				+ ", whUserCode=" + whUserCode + ", whUserFor=" + whUserFor
				+ ", whUserEmail=" + whUserEmail + ", whUserContact="
				+ whUserContact + ", whUserIdType=" + whUserIdType
				+ ", whIfOther=" + whIfOther + ", whUserIdNumber="
				+ whUserIdNumber + ", createdOn=" + createdOn
				+ ", modeifiedOn=" + modeifiedOn + "]";
	}
}