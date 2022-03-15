package com.sathyatech.app.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.sathyatech.app.model.ShipmentType;

public class ShipmentSpecification implements Specification<ShipmentType> {

	private ShipmentType filter;

	public ShipmentSpecification(ShipmentType filter) {
		this.filter = filter;
	}

	public Predicate toPredicate(Root<ShipmentType> root,CriteriaQuery<?> query, CriteriaBuilder cb){
		
		/*
		* Conjunction for AND operation :: Disjunction for OR operation
		*/
		Predicate p = cb.conjunction();
		
		//Modes
		if(!StringUtils.isEmpty(filter.getShipmentMode())){
			p.getExpressions()
			.add(cb.equal(root.get("shipmentMode"), filter.getShipmentMode()));
		}
		
		//Grades
		if(!StringUtils.isEmpty(filter.getShipmentGrade())){
			p.getExpressions()
				.add(cb.equal(root.get("shipmentGrade"), filter.getShipmentGrade()));
		}
		return p;
	}
}
