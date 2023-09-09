package org.foi.diplomski.msakac.odmaralica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import org.foi.diplomski.msakac.odmaralica.model.security.RefreshToken;
import org.foi.diplomski.msakac.odmaralica.model.security.UserToken;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @JoinColumn(name = "role_id")
    private Role role;

    @NotNull
    private Boolean activated;

    @Column(name = "policy_accepted")
    private Boolean policyAccepted;

    private String description;

    private String phoneNumber;

    // If user is deleted so are all refresh_tokens
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<RefreshToken> refreshTokens;

    // If user is deleted so are all user_tokens
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<UserToken> userTokens;

    // If user is deleted logs are detached
    @JsonIgnore
    @OneToMany(cascade = CascadeType.DETACH, mappedBy = "user")
    private List<Log> logs;

    // If user is deleted so are all reservations of user
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Reservation> reservations;

    // If user is deleted so are all residences of user
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "owner")
    private List<Residence> residences;

    // If creator is deleted so are images
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "createdBy")
    private List<Image> images;

    // If user is deleted so are images
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Image> imagesOwner;

    // If user is deleted so are images
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Review> reviews;

}
