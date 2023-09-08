package org.foi.diplomski.msakac.odmaralica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "region")
public class Region implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    // If Region is deleted so are all cities
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "region")
    private List<City> cities;
}
