package eu.unite.address.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import eu.unite.address.model.Address;

@Repository
public interface AddressesRepository extends JpaRepository<Address, UUID>, JpaSpecificationExecutor<Address> {
}
