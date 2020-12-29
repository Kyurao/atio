package com.kyurao.atio.service;

import com.kyurao.atio.domain.user.User;
import com.kyurao.atio.exception.NotFoundException;
import com.kyurao.atio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.kyurao.atio.domain.user.enums.UserStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean activateUser(String code) {
        return userRepository
                .findByAccountActivationCode(code)
                .map(this::activateUser)
                .orElse(false);
    }

    private boolean activateUser(User user) {
        user.getAccount().setActivationCode(null);
        user.getAccount().setStatus(ACTIVE);
        userRepository.save(user);

        return true;
    }

    public User getUser() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository
                .findByAccountEmail(email)
                .orElseThrow(() -> new NotFoundException("User with " + email + " not found"));
    }

}
