package uz.architect.springsecurityexample.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.architect.springsecurityexample.entity.User;
import uz.architect.springsecurityexample.enums.Status;

import java.util.Collection;

public class UserSecurity implements UserDetails {

    private final User user;

    public UserSecurity(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .toList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getStatus().name().equals(Status.ACTIVE.name());
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus().name().equals(Status.ACTIVE.name());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getStatus().name().equals(Status.ACTIVE.name());

    }

    @Override
    public boolean isEnabled() {
        return user.getStatus().name().equals(Status.ACTIVE.name());
    }
}
