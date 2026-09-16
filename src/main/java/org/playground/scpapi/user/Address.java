package org.playground.scpapi.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "addresses")
public class Address {
    @Id
    @Column(name = "uuid", nullable = false, length = 16)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(name = "building_number")
    private Integer buildingNumber;

    @Column(name = "district")
    private String district;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state_or_province")
    private String stateOrProvince;

    @Column(name = "postal_code")
    private Integer postalCode;

    @Column(name = "country_code")
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}