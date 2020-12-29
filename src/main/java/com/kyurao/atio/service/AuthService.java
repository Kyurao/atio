package com.kyurao.atio.service;

import com.kyurao.atio.domain.user.User;
import com.kyurao.atio.domain.user.enums.UserRole;
import com.kyurao.atio.domain.user.enums.UserStatus;
import com.kyurao.atio.dto.RegistrationReq;
import com.kyurao.atio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserMailService userMailService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegistrationReq req) {
        var user = new User();
        user.setName(req.getName());
        var account = user.getAccount();
        account.setEmail(req.getEmail().toLowerCase());
        account.setStatus(UserStatus.CREATED);
        account.setRoles(Set.of(UserRole.USER));
        account.setPassword(passwordEncoder.encode(req.getFirstPassword()));
        account.setActivationCode(randomAlphanumeric(10) + System.nanoTime());
        userRepository.save(user);

        userMailService.sendActivationMail(user);
    }
}
