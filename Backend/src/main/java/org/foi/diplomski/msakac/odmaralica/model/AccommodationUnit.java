package org.foi.diplomski.msakac.odmaralica.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accommodation_unit")
public class AccommodationUnit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "residence_id", referencedColumnName = "id", nullable = false)
    private Residence residence;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    private String unitSize;
    private String numOfGuests;
    private String beds;
    private Boolean privateKitchen;
    private Boolean privateBathroom;
    private Boolean terrace;
    private Boolean seaView;
    private Boolean tv;
    private Boolean pets;
    private Boolean smoking;

    // If accommodation unit is deleted so are all reservations
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "accommodationUnit")
    private List<Reservation> reservations;

    // If accommodation unit is deleted so are price periods
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "accommodationUnit")
    private List<PricePeriod> pricePeriods;

    // If accommodation unit is deleted so are images
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "accommodationUnit")
    private List<Image> images;
}
