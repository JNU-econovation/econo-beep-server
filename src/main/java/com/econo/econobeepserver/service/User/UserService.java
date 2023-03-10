package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.domain.User.User;
import com.econo.econobeepserver.domain.User.UserRepository;
import com.econo.econobeepserver.dto.User.*;
import com.econo.econobeepserver.exception.NotFoundUserException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private EconoIDPAdapter econoIDPAdapter;


    private User createUser(Long idpId) {
        return userRepository.save(
                UserSaveDto.builder()
                        .idpId(idpId)
                        .build()
                        .toEntity()
        );
    }

    public User getUserByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
    }

    public User getUserByIdpId(Long idpId) {
        return userRepository.findByIdpId(idpId).orElseThrow(NotFoundUserException::new);
    }

    public User getUserByIdpToken(String idpToken) {
        UserIdpTokenDto userIdpTokenDto = econoIDPAdapter.getUserIdpTokenDtoByIdpToken(idpToken);

        return userRepository.findByIdpId(userIdpTokenDto.getId())
                .orElse(createUser(userIdpTokenDto.getId()));
    }

    public UserProfileDto getUserProfileDtoByUserId(Long userId) {
        Long idpId = getUserByUserId(userId).getIdpId();
        UserIdpIdDto userIdpIdDto = econoIDPAdapter.getUserIdpIdDtoByIdpId(idpId);

        return new UserProfileDto(userId, userIdpIdDto);
    }

    public UserProfileDto getUserProfileDtoByIdpId(Long idpId) {
        Long userId = getUserByIdpId(idpId).getId();
        UserIdpIdDto userIdpIdDto = econoIDPAdapter.getUserIdpIdDtoByIdpId(idpId);

        return new UserProfileDto(userId, userIdpIdDto);
    }

    public UserRenterDto getUserRenterDtoByUserId(Long userId) {
        Long idpId = getUserByUserId(userId).getIdpId();
        UserIdpIdDto userIdpIdDto = econoIDPAdapter.getUserIdpIdDtoByIdpId(idpId);

        return new UserRenterDto(userId, userIdpIdDto);
    }
}
