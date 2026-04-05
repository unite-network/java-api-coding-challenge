package eu.unite.address.model;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  
  @Column(nullable = false)
  private UUID userId;
  
  private String addressName;
  private String street;
  private String city;
  private String postalCode;
  private String region;
  private String country;
  
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private AddressType type;
}
