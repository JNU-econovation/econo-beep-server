package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.domain.User.Role;
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

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundUserException::new);
    }

    public User getUserByIdpId(Long idpId) {
        return userRepository.findByIdpId(idpId).orElseThrow(NotFoundUserException::new);
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
        UserSaveDto userSaveDto = econoIDP.getUserInfoDtoByAccessToken(accessToken);
        syncUser(userSaveDto);

        return getUserByIdpId(userSaveDto.getIdpId());
    }

    public UserProfileDto getUserProfileDtoByAccessToken(String accessToken) {
        return new UserProfileDto(getUserByAccessToken(accessToken));
    }

    public long getUserIdByAccessToken(String accessToken) {
        return getUserByAccessToken(accessToken).getId();
    }

    public Role getUserRoleByAccessToken(String accessToken) {
        return getUserByAccessToken(accessToken).getRole();
    }
}
