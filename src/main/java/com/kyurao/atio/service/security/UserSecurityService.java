package com.kyurao.atio.service.security;

import com.kyurao.atio.domain.user.User;
import com.kyurao.atio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByAccountEmail(email)
                .map(User::getAccount)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
