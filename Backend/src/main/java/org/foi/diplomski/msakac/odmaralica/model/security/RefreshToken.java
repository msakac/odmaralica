package org.foi.diplomski.msakac.odmaralica.model.security;

import lombok.*;
import org.foi.diplomski.msakac.odmaralica.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "refresh_token")
public class RefreshToken implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "expires_at", nullable = false)
    private Timestamp expiresAt;

    @Column(name = "is_expired", nullable = false)
    private boolean isExpired;
}
