package org.foi.diplomski.msakac.odmaralica.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
public class Image implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "accommodation_unit_id")
    private AccommodationUnit accommodationUnit;

    @ManyToOne
    @JoinColumn(name = "residence_id")
    private Residence residence;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
}
