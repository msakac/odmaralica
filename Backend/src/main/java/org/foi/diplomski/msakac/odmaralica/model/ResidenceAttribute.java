package org.foi.diplomski.msakac.odmaralica.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "residence_attribute")
public class ResidenceAttribute implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "residence_id", referencedColumnName = "id", nullable = false)
    private Residence residence;

    @Column(name = "distance_from_sea")
    private Integer distanceFromSea;

    @Column(name = "distance_from_store")
    private String distanceFromStore;

    @Column(name = "distance_from_beach")
    private String distanceFromBeach;

    @Column(name = "distance_from_restoran")
    private String distanceFromRestoran;

    @Column(name = "distance_from_center")
    private String distanceFromCenter;

    @Column(name = "distance_from_bank")
    private String distanceFromBank;
}
