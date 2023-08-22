package org.foi.diplomski.msakac.odmaralica.model;

import lombok.*;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.foi.diplomski.msakac.odmaralica.model.security.RefreshToken;
import org.foi.diplomski.msakac.odmaralica.model.security.UserToken;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @NotNull
    private Boolean activated;

    private String description;

    private String phoneNumber;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<RefreshToken> refreshTokens;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<UserToken> userTokens;

    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "user")
    private List<Log> logs;

}
