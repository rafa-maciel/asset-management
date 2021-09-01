package com.rmaciel.academy.token.security.user;

import com.rmaciel.academy.core.models.UserAccount;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class UserAccountDetails implements UserDetails {
    private final UserAccount userAccount;

    public UserAccountDetails(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_" + userAccount.getProfile().toString());
    }

    @Override
    public String getPassword() {
        return userAccount.getPassword();
    }

    @Override
    public String getUsername() {
        return userAccount.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userAccount.isEnabled();
    }
}
