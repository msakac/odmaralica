package org.foi.diplomski.msakac.odmaralica.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "accommodation_unit_image")
public class AccommodationUnitImage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accommodation_unit_id", nullable = false)
    private AccommodationUnit accommodationUnit;

    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;
}
