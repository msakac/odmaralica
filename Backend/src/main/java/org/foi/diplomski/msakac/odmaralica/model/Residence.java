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
    //FIXME: mozda bi tip trebal biti entitet
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @NotNull
    private Boolean isPublished;

    // If residence is deleted so are all addresses
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "residence")
    private List<Address> addresses;

    // If residence is deleted so are all residence_attributes
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "residence")
    private List<ResidenceAttribute> residenceAttributes;

    // If residence is deleted so are all accommodation_units
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "residence")
    private List<AccommodationUnit> accommodationUnits;

    // residence is deleted so are images
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "residence")
    private List<Image> images;
}
