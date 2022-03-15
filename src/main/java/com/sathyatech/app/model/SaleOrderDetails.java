package com.sathyatech.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="so_dtls_tbl")
public class SaleOrderDetails {
	@Id
	@Column(name="so_dtl_id")
	@GeneratedValue(generator="so_dtl_id_gen")
	@GenericGenerator(name="so_dtl_id_gen",strategy="increment")
	private Long saleDtlId;
	
	@Column(name="so_dtl_ord_id")
	private Long saleOrdHdrId;
	
	@Column(name="so_dtl_slno")
	private Integer slno;
	
	@ManyToOne
	@JoinColumn(name="so_itm_id_fk")
	private Item itemDetails;
	
	
	@Column(name="so_dtl_basecost")
	private Double baseCost;
	
	@Column(name="so_dtl_itm_qty")
	private Long itemsQty;
	
	@Column(name="so_line_val")
	private Double lineValue;
	
	public SaleOrderDetails() {
		super();
	}

	public SaleOrderDetails(Long saleDtlId, Long saleOrdHdrId, Integer slno,
			Item itemDetails, Double baseCost, Long itemsQty, Double lineValue) {
		super();
		this.saleDtlId = saleDtlId;
		this.saleOrdHdrId = saleOrdHdrId;
		this.slno = slno;
		this.itemDetails = itemDetails;
		this.baseCost = baseCost;
		this.itemsQty = itemsQty;
		this.lineValue = lineValue;
	}

	public Long getSaleDtlId() {
		return saleDtlId;
	}

	public void setSaleDtlId(Long saleDtlId) {
		this.saleDtlId = saleDtlId;
	}

	public Long getSaleOrdHdrId() {
		return saleOrdHdrId;
	}

	public void setSaleOrdHdrId(Long saleOrdHdrId) {
		this.saleOrdHdrId = saleOrdHdrId;
	}

	public Integer getSlno() {
		return slno;
	}

	public void setSlno(Integer slno) {
		this.slno = slno;
	}

	public Item getItemDetails() {
		return itemDetails;
	}

	public void setItemDetails(Item itemDetails) {
		this.itemDetails = itemDetails;
	}

	public Double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}

	public Long getItemsQty() {
		return itemsQty;
	}

	public void setItemsQty(Long itemsQty) {
		this.itemsQty = itemsQty;
	}

	public Double getLineValue() {
		return lineValue;
	}

	public void setLineValue(Double lineValue) {
		this.lineValue = lineValue;
	}

	@Override
	public String toString() {
		return "SaleOrderDetails [saleDtlId=" + saleDtlId + ", saleOrdHdrId="
				+ saleOrdHdrId + ", slno=" + slno + ", itemDetails="
				+ itemDetails + ", baseCost=" + baseCost + ", itemsQty="
				+ itemsQty + ", lineValue=" + lineValue + "]";
	}
}
