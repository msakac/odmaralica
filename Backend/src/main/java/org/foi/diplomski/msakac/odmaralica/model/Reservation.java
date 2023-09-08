package org.foi.diplomski.msakac.odmaralica.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "accommodation_unit_id", nullable = false)
    private AccommodationUnit accommodationUnit;

    @Column(name = "start_at", nullable = false)
    private Date startAt;

    @Column(name = "end_at", nullable = false)
    private Date endAt;

    @Column(name = "note")
    private String note;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "cancelled", nullable = false)
    private boolean cancelled;
}
