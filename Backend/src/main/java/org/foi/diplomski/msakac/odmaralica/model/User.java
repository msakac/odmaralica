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

    // If user is deleted so are all refresh_tokens
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<RefreshToken> refreshTokens;

    // If user is deleted so are all user_tokens
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<UserToken> userTokens;

    // If user is deleted logs are detached
    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "user")
    private List<Log> logs;

    // If user is deleted so are all reservations of user
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Reservation> reservations;

    // If user is deleted so are all residences of user
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "owner")
    private List<Residence> residences;

    // If creator is deleted so are images
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "createdBy")
    private List<Image> images;

    // If user is deleted so are images
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Image> imagesOwner;

}
