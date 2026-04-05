package eu.unite.address.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.unite.address.model.AddressType;
import eu.unite.address.repository.AddressesRepository;
import eu.unite.address.repository.AddressSpecification;
import eu.unite.address.exception.ErrorResponse;

@RestController
@RequestMapping("/api/addresses")
public class AddressesController {

    private final AddressesRepository addressesRepository;

    public AddressesController(AddressesRepository addressesRepository) {
        this.addressesRepository = addressesRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAddresses(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String type) {

        AddressType addressType = null;
        if (type != null) {
            try {
                addressType = AddressType.valueOf(type);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(new ErrorResponse("Invalid address type: " + type));
            }
        }

        return ResponseEntity.ok(addressesRepository
                .findAll(AddressSpecification.buildUserAndTypeQuerySpecification(userId, addressType)));
    }
}
