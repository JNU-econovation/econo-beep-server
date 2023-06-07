package com.econo.econobeepserver.service.User;

import com.econo.econobeepserver.domain.User.Role;
import com.econo.econobeepserver.domain.User.User;
import com.econo.econobeepserver.domain.User.UserRepository;
import com.econo.econobeepserver.dto.User.UserIdpIdDto;
import com.econo.econobeepserver.dto.User.UserIdpTokenDto;
import com.econo.econobeepserver.exception.NotFoundUserException;
import com.econo.econobeepserver.service.User.UserIdp;
import com.econo.econobeepserver.service.User.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

//    // Target
//    @InjectMocks
//    UserService userService;
//    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    // Mock objects injected into UserService
//    @Mock
//    UserRepository userRepository;
//    @Mock
//    UserIdp userIdp;
//    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    // User for test //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    final User user = User.builder()
//            .idpId(1L)
//            .role(Role.USER)
//            .build();
//    final UserIdpIdDto userIdpIdDto = UserIdpIdDto.builder()
//            .id(1L)
//            .year(21)
//            .username("에코노")
//            .email("econo@gmail.com")
//            .build();
//    final UserIdpTokenDto userIdpTokenDto = UserIdpTokenDto.builder()
//            .id(1L)
//            .year(21)
//            .username("에코노")
//            .email("econo@gmail.com")
//            .build();
//    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//    @Nested
//    @DisplayName("getUserByUserId 테스트")
//    class Test_GetUserByUserId {
//
//        @DisplayName("정상 반환 테스트")
//        @Test
//        void test() {
//            // given
//            given(userRepository.findById(1L)).willReturn(Optional.of(user));
//
//            // when
//            User result = userService.getUserByUserId(1L);
//
//            // then
//            assertEquals(user.getIdpId(), result.getIdpId());
//        }
//
//        @DisplayName("예외 반환 테스트")
//        @Test
//        void test_exception() {
//            // given
//            given(userRepository.findById(1L)).willReturn(Optional.empty());
//
//            // when & then
//            assertThrows(NotFoundUserException.class, () -> userService.getUserByUserId(1L));
//        }
//    }
}
