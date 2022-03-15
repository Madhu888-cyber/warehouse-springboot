package com.sathyatech.app.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import com.sathyatech.app.model.SaleOrder;

public class SaleOrderSpecification implements Specification<SaleOrder>{
	
	private SaleOrder filter;

	public SaleOrderSpecification(SaleOrder filter) {
		super();
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<SaleOrder> root, CriteriaQuery<?> arg1,
			CriteriaBuilder cb) {
		Predicate p = cb.conjunction();
		
		if(!StringUtils.isEmpty(filter.getOrderCode())){
			p.getExpressions().add(cb.equal(root.get("orderCode"), filter.getOrderCode()));
		}
		
		if(!StringUtils.isEmpty(filter.getStockSource())){
			p.getExpressions().add(cb.equal(root.get("stockSource"), filter.getStockSource()));
		}
		return p;
	}
}
