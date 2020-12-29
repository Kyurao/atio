package com.kyurao.atio.domain.user;

import com.kyurao.atio.domain.user.enums.UserRole;
import com.kyurao.atio.domain.user.enums.UserStatus;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Parent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.kyurao.atio.domain.user.enums.UserStatus.*;

@Getter
@Setter
@EqualsAndHashCode(of = {"email"})
@Embeddable
public class Account implements UserDetails {

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD_HASH",nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS",nullable = false)
    private UserStatus status;

    @Column(name = "ACTIVATION_CODE")
    private String activationCode;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID",referencedColumnName = "ID"))
    private Set<UserRole> roles = new HashSet<>();

    @Setter(AccessLevel.PROTECTED)
    @Parent
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status != DELETED;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status != BANNED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status == ACTIVE;
    }
}
