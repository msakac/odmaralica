package org.foi.diplomski.msakac.odmaralica.model.security;

import lombok.*;
import javax.persistence.*;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.utils.SecurityConstants;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_token")
public class UserToken implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="token")
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="type")
    private TokenType type;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "expires_at", nullable = false)
    private Timestamp expiresAt;

    @Column(name= "is_used", nullable = false)
    private boolean isUsed;

    public UserToken(User user, TokenType type) {
        this.user = user;
        this.type = type;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.isUsed = false;
        if(type.equals(TokenType.Activation)){
            this.expiresAt = new Timestamp(System.currentTimeMillis() + SecurityConstants.ACTIVATION_TOKEN_EXPIRATION_TIME);
        }else if(type.equals(TokenType.PasswordReset)){
            this.expiresAt = new Timestamp(System.currentTimeMillis() + SecurityConstants.PASSWORD_RESET_TOKEN_EXPIRATION_TIME);
        }
        this.token = UUID.randomUUID().toString();
    }
}