package org.foi.diplomski.msakac.odmaralica.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "amenity")
public class Amenity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accommodation_unit_id", nullable = false)
    private AccommodationUnit accommodationUnit;

    @Column(name = "bathroom")
    private Byte bathroom;

    @Column(name = "kitchen")
    private Byte kitchen;

    @Column(name = "parking")
    private Byte parking;

    @Column(name = "terrace")
    private Byte terrace;

    @Column(name = "garage")
    private Byte garage;

    @Column(name = "sea_view")
    private Byte seaView;

    @Column(name = "wifi")
    private Byte wifi;
}
