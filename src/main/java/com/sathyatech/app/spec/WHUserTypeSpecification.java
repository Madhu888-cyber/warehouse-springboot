package com.sathyatech.app.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import com.sathyatech.app.model.WHUserType;

public class WHUserTypeSpecification implements Specification<WHUserType>{
	
	private WHUserType filter;
	
	public WHUserTypeSpecification(WHUserType filter) {
		this.filter = filter;
	}

	public Predicate toPredicate(Root<WHUserType> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
		Predicate p= cb.conjunction();
		
		if(!StringUtils.isEmpty(filter.getUserType())){
			p.getExpressions()
			.add(cb.equal(root.get("userType"), filter.getUserType()));
		}
		
		if(!StringUtils.isEmpty(filter.getWhUserIdType())){
			p.getExpressions()
			.add(cb.equal(root.get("whUserIdType"), filter.getWhUserIdType()));
		}
		return p;
	}
}
