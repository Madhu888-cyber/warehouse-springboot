package com.sathyatech.app.model;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
@Table(name="order_method_tab")
@EntityListeners(AuditingEntityListener.class)
public class OrderMethod {
	@Id
	@Column(name="ord_id")
	@GeneratedValue(generator="order_id_gen")
	@GenericGenerator(name="order_id_gen",strategy="increment")
	private Long orderId;
	
	@Column(name="ord_mode")
	private String orderMode;
	
	@Column(name="ord_code")
	private String orderCode;
	
	@Column(name="ord_method")
	private String orderMethd;
	
	@Column(name="ord_accept")	
	@ElementCollection
	private Collection<String> orderAccept;
	
	
	@Column(name="ord_desc")
	private String description;
	
	@Column(name="ord_c_dte")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createdOn;
	
	@Column(name="ord_m_dte")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date lastModefiedOn;

	public OrderMethod() {
		super();
	}

	public OrderMethod(Long orderId) {
		super();
		this.orderId = orderId;
	}

	//---------Multipart Constructor---------
		public OrderMethod(String orderMode, String orderMethd,
				Collection<String> orderAccept, String description, String orderCode) {
			super();
			this.orderMode = orderMode;		
			this.orderMethd = orderMethd;
			this.orderAccept = orderAccept;
			this.description = description;
			this.orderCode = orderCode;
		}

		public Long getOrderId() {
			return orderId;
		}

		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}

		public String getOrderMode() {
			return orderMode;
		}

		public void setOrderMode(String orderMode) {
			this.orderMode = orderMode;
		}

		public String getOrderCode() {
			return orderCode;
		}

		public void setOrderCode(String orderCode) {
			this.orderCode = orderCode;
		}

		public String getOrderMethd() {
			return orderMethd;
		}

		public void setOrderMethd(String orderMethd) {
			this.orderMethd = orderMethd;
		}

		public Collection<String> getOrderAccept() {
			return orderAccept;
		}

		public void setOrderAccept(Collection<String> orderAccept) {
			this.orderAccept = orderAccept;
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

		public Date getLastModefiedOn() {
			return lastModefiedOn;
		}

		public void setLastModefiedOn(Date lastModefiedOn) {
			this.lastModefiedOn = lastModefiedOn;
		}

		@Override
		public String toString() {
			return "OrderMethod [orderId=" + orderId + ", orderMode="
					+ orderMode + ", orderCode=" + orderCode + ", orderMethd="
					+ orderMethd + ", orderAccept=" + orderAccept
					+ ", description=" + description + ", createdOn="
					+ createdOn + ", lastModefiedOn=" + lastModefiedOn + "]";
		}
		
		
}
