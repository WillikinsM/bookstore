package com.will.bookstoreapi.auth.appUser;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.will.bookstoreapi.auth.appUser.AppUserPermission.*;

public enum AppUserRole {
    USER(Sets.newHashSet(BOOK_READ,CATEGORY_READ)),
    ADMIN(Sets.newHashSet(USER_READ,USER_WRITE,BOOK_READ,BOOK_WRITE,CATEGORY_READ,CATEGORY_WRITE));

    private final Set<AppUserPermission> permissionSet;

    AppUserRole(Set<AppUserPermission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> permissions = getPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
    public Set<AppUserPermission> getPermissionSet() {
        return permissionSet;
    }
}
