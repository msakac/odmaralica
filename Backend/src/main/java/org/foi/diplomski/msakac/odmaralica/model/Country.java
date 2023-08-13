package org.foi.diplomski.msakac.odmaralica.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "country", schema = "public")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String name;

    @Column(name = "country_code", unique = true)
    @NotNull
    @Size(min = 2, max = 2, message = "Country code must be exactly 2 characters long")
    private String countryCode;
}
