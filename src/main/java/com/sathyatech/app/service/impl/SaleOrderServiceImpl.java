package com.sathyatech.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sathyatech.app.model.SaleOrder;
import com.sathyatech.app.repo.SaleOrderRepository;
import com.sathyatech.app.service.ISaleOrderService;

@Service
public class SaleOrderServiceImpl implements ISaleOrderService {

	@Autowired
	private SaleOrderRepository repo;
	
	public Long saveSale(SaleOrder saleOrder) {
		return repo.save(saleOrder).getSaleId();
	}
	
	public Page<SaleOrder> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	@Override
	public Page<SaleOrder> findAll(Specification<SaleOrder> spec,
			Pageable pageable) {
		return repo.findAll(spec, pageable);
	}
	
	public SaleOrder findById(Long saleId) {
		return repo.findOne(saleId);
	}
	
	public void delete(Long saleId) {
		repo.delete(saleId);
	}
	
	public boolean isExist(Long saleId) {
		return repo.exists(saleId);
	}
	
	public void update(SaleOrder saleOrder) {
		repo.save(saleOrder);
	}
	
	public void saveFile(List<SaleOrder> saleOrders) {
		repo.save(saleOrders);
	}
	
	public List<SaleOrder> findAll() {
		return repo.findAll();
	}
}
