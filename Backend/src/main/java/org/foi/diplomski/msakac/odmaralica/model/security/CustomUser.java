package org.foi.diplomski.msakac.odmaralica.model.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //TODO dodati za aktivaciju i ostala sranja
    /*
     * boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
    */
    public CustomUser(String username, String password,  Collection<? extends GrantedAuthority> authorities, Long id) {
        super(username, password, authorities);
        setId(id);
    }
}