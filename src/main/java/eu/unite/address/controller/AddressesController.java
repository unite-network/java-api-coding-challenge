package eu.unite.address.controller;

import eu.unite.address.dto.AddressCreateRequestDTO;
import eu.unite.address.model.Address;
import eu.unite.address.model.AddressType;
import eu.unite.address.repository.AddressSpecification;
import eu.unite.address.repository.AddressesRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Address> createAddress(@Valid @RequestBody AddressCreateRequestDTO request) {
        Address address = Address.builder()
                .userId(request.getUserId())
                .addressName(request.getAddressName())
                .street(request.getStreet())
                .city(request.getCity())
                .postalCode(request.getPostalCode())
                .region(request.getRegion())
                .country(request.getCountry())
                .type(request.getType())
                .build();

        Address savedAddress = addressesRepository.save(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }
}
