package com.sathyatech.app.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.sathyatech.app.model.Item;

public class ItemSpecification implements Specification<Item>{
	
	private Item filter;
	
	public ItemSpecification(Item filter) {
		this.filter = filter;
	}

	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Predicate p = cb.conjunction();
		
		if(!StringUtils.isEmpty(filter.getItemCode())){
			p.getExpressions()
			.add(cb.equal(root.get("itemCode").as(String.class), filter.getItemCode()));
		}		
		
		if(!StringUtils.isEmpty(filter.getItemBaseCost())){
			p.getExpressions()
			.add(cb.like(root.get("itemBaseCost").as(String.class), "%"+filter.getItemBaseCost()+"%"));
		}
		return p;
	}
}