package org.foi.diplomski.msakac.odmaralica.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "price_period")
public class PricePeriod implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_unit_id", nullable = false)
    private AccommodationUnit accommodationUnit;

    @Column(name = "start_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startAt;

    @Column(name = "end_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amount_id", nullable = false)
    private Amount amount;
}
