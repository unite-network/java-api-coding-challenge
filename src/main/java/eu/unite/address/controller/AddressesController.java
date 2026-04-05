package eu.unite.address.controller;

import eu.unite.address.model.AddressType;
import eu.unite.address.repository.AddressSpecification;
import eu.unite.address.repository.AddressesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/addresses")
public class AddressesController {

    private final AddressesRepository addressesRepository;

    public AddressesController(AddressesRepository addressesRepository) {
        this.addressesRepository = addressesRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAddresses(
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) AddressType addressType) {
        return ResponseEntity.ok(addressesRepository
                .findAll(AddressSpecification.buildUserAndTypeQuerySpecification(userId, addressType)));
    }
}
