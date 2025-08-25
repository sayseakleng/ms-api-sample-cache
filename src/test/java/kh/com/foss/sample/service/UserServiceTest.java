package kh.com.foss.sample.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import kh.com.foss.sample.constant.GenderType;
import kh.com.foss.sample.dto.UserCreationInputDto;
import kh.com.foss.sample.dto.UserResultDto;
import kh.com.foss.sample.dto.UserUpdateInputDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    void register() {
        log.info("--> Start register()");
        UserCreationInputDto userCreationInputDto = new UserCreationInputDto();
        userCreationInputDto.setFirstName("Dara");
        userCreationInputDto.setLastName("Sok");
        userCreationInputDto.setGender(GenderType.M);
        userCreationInputDto.setPhone("011111111");
        UserResultDto register = userService.register(userCreationInputDto);
        assertNotNull(register);
        log.info("<-- End register() with result: {}", register);
    }

    @Test
    void update() {
        log.info("--> Start update()");
        UserUpdateInputDto userUpdateInputDto = new UserUpdateInputDto();
        userUpdateInputDto.setUserId(1L);
        userUpdateInputDto.setLastName("Dara 2");
        UserResultDto update = userService.update(userUpdateInputDto);
        assertNotNull(update);
        log.info("<-- End update() with result: {}", update);
    }

    @Test
    void getUserByUserId() {
        log.info("--> Start getUserByUserId()");
        UserResultDto userByUserId = userService.getUserByUserId(1L);
        assertNotNull(userByUserId);
        log.info("<-- End getUserByUserId() with result: {}", userByUserId);
    }
}
