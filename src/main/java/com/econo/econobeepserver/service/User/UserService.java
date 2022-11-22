package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.domain.User.User;
import com.econo.econobeepserver.domain.User.UserRepository;
import com.econo.econobeepserver.dto.User.UserProfileDto;
import com.econo.econobeepserver.dto.User.UserSaveDto;
import com.econo.econobeepserver.exception.NotFoundUserException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private EconoIDP econoIDP;


    private User createUser(UserSaveDto userSaveDto) {
        return userRepository.save(userSaveDto.toEntity());
    }

    public User getUserByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
    }

    public User getUserByIdpId(Long idpId) {
        return userRepository.findByIdpId(idpId).orElseThrow(NotFoundUserException::new);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
    }

    public UserProfileDto getUserProfileDtoByUserId(Long userId) {
        return new UserProfileDto(getUserByUserId(userId));
    }

    @Transactional
    public void syncUser(UserSaveDto userSaveDto) {
        if (userRepository.existsByIdpId(userSaveDto.getIdpId())) {
            User user = getUserByIdpId(userSaveDto.getIdpId());
            user.update(userSaveDto);

        } else {
            createUser(userSaveDto);
        }
    }

    public User getUserByAccessToken(String accessToken) {
        UserSaveDto userSaveDto = econoIDP.getUserSaveDtoByAccessToken(accessToken);
        syncUser(userSaveDto);

        return getUserByEmail(userSaveDto.getEmail());
    }

    public long getUserIdByAccessToken(String accessToken) {
        return getUserByAccessToken(accessToken).getId();
    }
}
