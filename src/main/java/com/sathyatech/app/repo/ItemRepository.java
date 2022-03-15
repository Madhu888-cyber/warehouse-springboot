package com.sathyatech.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sathyatech.app.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>,JpaSpecificationExecutor<Item>{
	
	@Query("select itm from com.sathyatech.app.model.Item itm inner join itm.vendors ven where ven.whUserId=?1")
	public List<Item> findItemsByVendor(Long vendorId);
	
	@Query("select itm from com.sathyatech.app.model.Item itm inner join itm.customers cust where cust.whUserId=?1")
	public List<Item> findItemByCustomer(Long id);
}
