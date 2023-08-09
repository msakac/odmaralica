package org.foi.diplomski.msakac.odmaralica.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "residence_image")
public class ResidenceImage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "residence_id", nullable = false)
    private Residence residence;

    @ManyToOne
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;
}
