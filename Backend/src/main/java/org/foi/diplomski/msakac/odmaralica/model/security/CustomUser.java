package org.foi.diplomski.msakac.odmaralica.model.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
public class CustomUser extends User implements OAuth2User {

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    private Map<String, Object> attributes;

    //TODO dodati za aktivaciju i ostala sranja
    /*
     * boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
     */
    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id) {
        super(username, password, authorities);
        setId(id);
    }

    public static CustomUser create(org.foi.diplomski.msakac.odmaralica.model.User user, Map<String, Object> attributes) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("user"));

        return new CustomUser(
                user.getEmail(),
                user.getPassword(),
                authorities,
                user.getId()
        );
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }
}