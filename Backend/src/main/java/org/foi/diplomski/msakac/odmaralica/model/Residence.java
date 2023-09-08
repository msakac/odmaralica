package org.foi.diplomski.msakac.odmaralica.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "residence")
public class Residence implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "residence_type_id", nullable = false)
    private ResidenceType type;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @NotNull
    private Boolean isPublished;

    private Boolean isParkingFree;

    private Boolean isWifiFree;

    private Boolean isAirConFree;

    private String distanceSea;

    private String distanceStore;

    private String distanceBeach;

    private String distanceCenter;

    // If residence is deleted so are all addresses
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "residence")
    private List<Address> addresses;

    // If residence is deleted so are all accommodation_units
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "residence")
    private List<AccommodationUnit> accommodationUnits;

    // residence is deleted so are images
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "residence")
    private List<Image> images;

    // residence is deleted so are reviews
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "residence")
    private List<Review> reivews;
}
