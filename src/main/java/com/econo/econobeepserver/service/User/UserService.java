package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.domain.User.User;
import com.econo.econobeepserver.domain.User.UserRepository;
import com.econo.econobeepserver.dto.User.*;
import com.econo.econobeepserver.exception.IDPServerErrorException;
import com.econo.econobeepserver.exception.NotFoundUserException;
import com.econo.econobeepserver.exception.WrongAccessTokenException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private UserIdp userIdp;


    private User create(Long idpId) {
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

    public User loadUserByIdpToken(String idpToken) throws WrongAccessTokenException, IDPServerErrorException {
        UserIdpTokenDto userIdpTokenDto = userIdp.getUserIdpTokenDtoByIdpToken(idpToken);

        return userRepository.findByIdpId(userIdpTokenDto.getId())
                .orElseGet(() -> create(userIdpTokenDto.getId()));
    }

    public UserProfileDto getUserProfileDtoByUserId(Long userId) throws WrongAccessTokenException, IDPServerErrorException {
        Long idpId = getUserByUserId(userId).getIdpId();
        UserIdpIdDto userIdpIdDto = userIdp.getUserIdpIdDtoByIdpId(idpId);

        return new UserProfileDto(userId, userIdpIdDto);
    }

    public UserProfileDto getUserProfileDtoByIdpId(Long idpId) throws WrongAccessTokenException, IDPServerErrorException {
        Long userId = getUserByIdpId(idpId).getId();
        UserIdpIdDto userIdpIdDto = userIdp.getUserIdpIdDtoByIdpId(idpId);

        return new UserProfileDto(userId, userIdpIdDto);
    }

    public UserRenterDto getUserRenterDtoByUserId(Long userId) throws WrongAccessTokenException, IDPServerErrorException {
        Long idpId = getUserByUserId(userId).getIdpId();
        UserIdpIdDto userIdpIdDto = userIdp.getUserIdpIdDtoByIdpId(idpId);

        return new UserRenterDto(userId, userIdpIdDto);
    }
}