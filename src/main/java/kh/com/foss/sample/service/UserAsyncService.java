package kh.com.foss.sample.service;

import java.util.concurrent.CompletableFuture;
import kh.com.foss.sample.dto.UserResultDto;

public interface UserAsyncService {
    CompletableFuture<UserResultDto> getUserByUserId(Long userId);
}
