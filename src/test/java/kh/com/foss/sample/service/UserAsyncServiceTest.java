package kh.com.foss.sample.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import kh.com.foss.sample.constant.GenderType;
import kh.com.foss.sample.dto.UserCreationInputDto;
import kh.com.foss.sample.dto.UserResultDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
class UserAsyncServiceTest {
    @Autowired
    private UserAsyncService asyncService;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("applicationTaskExecutor")
    private Executor executor;

    @Test
    @Order(1)
    void register() {
        log.info("--> Start register()");
        UserCreationInputDto userCreationInputDto = new UserCreationInputDto();
        userCreationInputDto.setFirstName("Dara");
        userCreationInputDto.setLastName("Sok");
        userCreationInputDto.setGender(GenderType.M);
        userCreationInputDto.setPhone("011111112");
        UserResultDto register = userService.register(userCreationInputDto);
        assertNotNull(register);
        log.info("<-- End register() with result: {}", register);
    }

    @Test
    void getUserByUserId1() throws InterruptedException, ExecutionException {
        log.info("--> Start getUserByUserId1()");
        asyncService
                .getUserByUserId(1L)
                .thenAccept((user) -> {
                    log.info("<-- End getUserByUserId1() with result: {}", user);
                })
                .join();
    }

    @Test
    void getUserByUserId2() throws InterruptedException, ExecutionException {
        log.info("--> Start getUserByUserId2()");
        CompletableFuture.<UserResultDto>supplyAsync(
                        () -> {
                            return userService.getUserByUserId(1L);
                        },
                        executor)
                .thenAccept((user) -> {
                    log.info("<-- End getUserByUserId2() with result: {}", user);
                })
                .join();
    }
}
