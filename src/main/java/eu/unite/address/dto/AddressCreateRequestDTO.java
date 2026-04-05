package eu.unite.address.dto;

import eu.unite.address.model.AddressType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreateRequestDTO {
    @NotNull(message = "userId is required")
    private UUID userId;
    
    private String addressName;
    private String street;
    private String city;
    private String postalCode;
    private String region;
    private String country;
    
    @NotNull(message = "type is required")
    private AddressType type;
}
