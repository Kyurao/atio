package com.kyurao.atio.service;

import com.kyurao.atio.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Async
@Service
@RequiredArgsConstructor
public class UserMailService {

    private static final String ACTIVATION_SUBJECT = "Activation code";
    private static final String RESET_SUBJECT = "Reset password";

    private final EmailService emailService;

    @Value("${atio.base.url}")
    private String baseUrl;

    @Async
    void sendActivationMail(User user) {
        if (!StringUtils.isEmpty(user.getAccount().getEmail())) {
            String message = String.format(
                        "Hello, %s! %nWelcome to Atio. Please, visit next link: " + baseUrl + "/activate/%s",
                    user.getName(),
                    user.getAccount().getActivationCode()
            );

            emailService
                    .newEmail(ACTIVATION_SUBJECT)
                    .content(message)
                    .to(user.getAccount().getEmail())
                    .send();
        } else {
            log.error("");
        }
    }

    @Async
    void sendResetPasswordMail(User user) {
        if (!StringUtils.isEmpty(user.getAccount().getEmail())) {
            String message = String.format(
                    "Hello, %s! %nFor reset your password, visit next link: " + baseUrl + "/reset/%s",
                    user.getName(),
                    user.getAccount().getActivationCode()
            );

            emailService
                    .newEmail(RESET_SUBJECT)
                    .content(message)
                    .to(user.getAccount().getEmail())
                    .send();
        }
    }
}
