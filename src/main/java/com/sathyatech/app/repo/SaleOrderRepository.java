package com.sathyatech.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.sathyatech.app.model.SaleOrder;

public interface SaleOrderRepository extends JpaRepository<SaleOrder, Long>,JpaSpecificationExecutor<SaleOrder>{
	
}
