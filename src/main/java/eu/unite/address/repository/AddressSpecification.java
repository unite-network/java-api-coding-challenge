package eu.unite.address.repository;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import eu.unite.address.model.Address;
import eu.unite.address.model.AddressType;
import jakarta.persistence.criteria.Predicate;

public class AddressSpecification {

    public static Specification<Address> buildUserAndTypeQuerySpecification(UUID userId, AddressType type) {
        return (root, query, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (userId != null) {
                predicates.add(criteriaBuilder.equal(root.get("userId"), userId));
            }

            if (type != null) {
                predicates.add(criteriaBuilder.equal(root.get("type"), type));
            }

            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
