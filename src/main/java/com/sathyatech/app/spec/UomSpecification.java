package com.sathyatech.app.spec;



import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.sathyatech.app.model.Uom;


public class UomSpecification implements Specification<Uom> {
	
	private Uom filter;
	
	public UomSpecification(Uom filter) {
		super();
		this.filter = filter;
	}

	public Predicate toPredicate(Root<Uom> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
		
		Predicate p= cb.conjunction();
		
		if(filter.getUomType()!=null && !"".equals(filter.getUomType())){
			List<Expression<Boolean>> expList= p.getExpressions();
			expList.add(cb.equal(root.get("uomType"),filter.getUomType()));
			
		}
		
		if(filter.getUomModel()!=null && !"".equals(filter.getUomModel())){
			List<Expression<Boolean>> expList= p.getExpressions();
			expList.add(cb.like(root.get("uomModel").as(String.class), "%"+filter.getUomModel()+"%"));
		}
		
		if(filter.getDescription()!=null && !"".equals(filter.getDescription())){
			p.getExpressions()
			.add(cb.like(root.get("description").as(String.class), "%"+filter.getDescription()+"%"));
		}
		return p;
	}
}
