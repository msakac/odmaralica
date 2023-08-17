package org.foi.diplomski.msakac.odmaralica.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "amount")
public class Amount implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "currency", length = 3, nullable = false)
    private String currency;

    //TODO: Na ovaj nacin brisem sve pricePeriods koji su vezani za ovaj amount
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "amount")
    private List<PricePeriod> pricePeriods;
}
