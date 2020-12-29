package com.kyurao.atio.service;

import com.kyurao.atio.domain.common.Location;
import com.kyurao.atio.domain.user.ContactInfo;
import com.kyurao.atio.domain.user.User;
import com.kyurao.atio.dto.ProfileDto;
import com.kyurao.atio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;

    private final UserService userService;


    @Transactional
    public User editUser(ProfileDto dto) {
        var user = userService.getUser();
        user.setName(dto.getName());
        if (isNull(user.getContactInfo())) {
            user.setContactInfo(new ContactInfo());
            if (isNull(user.getContactInfo().getLocation())) {
                user.getContactInfo().setLocation(new Location());
            }
        }
        user.getContactInfo().setPhoneNumber(dto.getPhoneNumber());
        var location = user.getContactInfo().getLocation();
        location.setCountry(dto.getCountry());
        location.setRegion(dto.getRegion());
        location.setCity(dto.getCity());

        return userRepository.save(user);
    }

    public ProfileDto toProfileDto(User user) {
        var dto = new ProfileDto();
        dto.setName(user.getName());
        if (nonNull(user.getContactInfo())) {
            dto.setPhoneNumber(user.getContactInfo().getPhoneNumber());
            dto.setCountry(user.getContactInfo().getLocation().getCountry());
            dto.setRegion(user.getContactInfo().getLocation().getRegion());
            dto.setCity(user.getContactInfo().getLocation().getCity());
        }
        return dto;
    }
}
