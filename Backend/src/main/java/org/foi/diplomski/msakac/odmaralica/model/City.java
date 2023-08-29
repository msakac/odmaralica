package org.foi.diplomski.msakac.odmaralica.model;

import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city")
public class City implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "zip", nullable = false, unique = true)
    private String zip;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    // If city is deleted so are all addresses
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "city")
    private List<Address> addresses;
}
