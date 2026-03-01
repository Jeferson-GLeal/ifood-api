package com.ifood.infrastructure.repository.spec;

import com.ifood.domain.model.Restaurante;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteComFreteGratisSpec implements Specification<Restaurante> {

    private static final long serialVersionUID = 1L;

    @Override
    public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {

        return builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }
}
