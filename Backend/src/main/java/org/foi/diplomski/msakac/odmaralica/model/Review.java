package org.foi.diplomski.msakac.odmaralica.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "residence_id", referencedColumnName = "id", nullable = false)
    private Residence residence;


    @DecimalMin(value = "1", inclusive = false, message = "Grade must be bigger than 1")
    @DecimalMax(value = "6", inclusive = false, message = "Grade must be smaller than 5")
    @Column(name = "grade", nullable = false)
    private double grade;

    @Column(name = "message")
    private String message;
}
