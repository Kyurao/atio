package com.kyurao.atio.util;

import com.kyurao.atio.domain.user.Account;
import com.kyurao.atio.domain.user.User;
import com.kyurao.atio.exception.UserException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityUtils {

    public static User currentUser() {
        final var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.getClass() == Account.class) {
            return ((Account) principal).getUser();
        } else {
            throw new UserException("User is not authenticated");
        }
    }
}
