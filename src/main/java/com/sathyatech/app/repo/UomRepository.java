package com.sathyatech.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sathyatech.app.model.Uom;

public interface UomRepository extends JpaRepository<Uom, Long>,JpaSpecificationExecutor<Uom> {

	@Query("select count(uomId) from com.sathyatech.app.model.Uom where uomModel=?1 and uomType=?2")
	public Long countUomModelAndType(String uomModel,String uomType);
	
	//Fetch Uom based on UomModel
	public Uom findByUomModel(String uomModel);
}
