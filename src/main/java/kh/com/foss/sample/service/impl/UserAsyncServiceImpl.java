package kh.com.foss.sample.service.impl;

import java.util.concurrent.CompletableFuture;
import kh.com.foss.sample.dto.UserResultDto;
import kh.com.foss.sample.service.UserAsyncService;
import kh.com.foss.sample.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAsyncServiceImpl implements UserAsyncService {
    private final UserService userService;

    @Override
    @Async
    public CompletableFuture<UserResultDto> getUserByUserId(Long userId) {
        log.debug("Inquiry for {}", userId);
        return CompletableFuture.completedFuture(userService.getUserByUserId(userId));
    }
}
