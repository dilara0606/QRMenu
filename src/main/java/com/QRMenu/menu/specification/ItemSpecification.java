package com.QRMenu.menu.specification;

import com.QRMenu.menu.entity.Item;
import com.QRMenu.menu.filter.ItemFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ItemSpecification {
    public static Specification<Item> searchItem(ItemFilter itemFilter) {
        return (Root< Item > root, CriteriaQuery< ?> query, CriteriaBuilder builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (itemFilter.getName() != null && !itemFilter.getName().isEmpty()) {
                Predicate namePredicate = builder.like(builder.lower(root.get("name")),
                        "%" + itemFilter.getName().toLowerCase() + "%");
                predicates.add(namePredicate);
            }

            if (itemFilter.getDescription() != null && !itemFilter.getDescription().isEmpty()) {
                Predicate surnamePredicate = builder.like(builder.lower(root.get("description")),
                        "%" + itemFilter.getDescription().toLowerCase() + "%");
                predicates.add(surnamePredicate);
            }

            if (!predicates.isEmpty()) {
                return builder.or(predicates.toArray(new Predicate[0]));
            } else {
                return builder.conjunction();
            }
        };
    }
}
