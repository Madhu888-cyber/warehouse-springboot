package com.sathyatech.app.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.sathyatech.app.model.OrderMethod;

public class OrderSpecification implements Specification<OrderMethod> {
	
	private OrderMethod filter;
	
	public OrderSpecification(OrderMethod filter) {
		super();
		this.filter = filter;
	}

	public Predicate toPredicate(Root<OrderMethod> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		Predicate p=cb.conjunction();

		if(filter.getOrderMode()!=null && !"".equals(filter.getOrderMode().trim())){
			p.getExpressions().add(
					cb.equal(root.get("orderMode"), filter.getOrderMode())
				);
			}
		
		if(filter.getOrderMethd()!=null && !"".equals(filter.getOrderMethd().trim())){
			p.getExpressions().add(
					cb.like(root.get("orderMethd").as(String.class), "%"+filter.getOrderMethd()+"%")
				);
			}
		
		/*if(filter.getOrderAccept()!=null && !"".equals(filter.getOrderAccept())){
			
			p.getExpressions().add(root.get("orderAccept").as(Collections.class).in(filter.getOrderAccept()));
			}*/
		
		if( filter.getDescription()!=null && !"".equals(filter.getDescription().trim())){
			p.getExpressions().add(
		cb.like(root.get("description").as(String.class), "%"+filter.getDescription()+"%")	
				);
		}

		return p;
	}
}
