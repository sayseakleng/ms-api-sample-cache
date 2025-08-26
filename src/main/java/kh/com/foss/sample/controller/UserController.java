package kh.com.foss.sample.controller;

import java.util.concurrent.ExecutionException;
import kh.com.foss.sample.dto.UserCreationInputDto;
import kh.com.foss.sample.dto.UserResultDto;
import kh.com.foss.sample.mapper.UserVoMapper;
import kh.com.foss.sample.service.UserService;
import kh.com.foss.sample.vo.request.UserCreationRequestVo;
import kh.com.foss.sample.vo.response.UserCreationResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserVoMapper userVoMapper;

    @PostMapping
    public UserCreationResponseVo register(@Validated @RequestBody UserCreationRequestVo userCreationRequestVO) {
        UserCreationInputDto userCreationInputDto = userVoMapper.to(userCreationRequestVO);
        UserResultDto userResultDto = userService.register(userCreationInputDto);
        return userVoMapper.from(userResultDto);
    }

    @GetMapping("/{userId}")
    public UserCreationResponseVo getUserByUserId(@PathVariable Long userId) throws InterruptedException, ExecutionException {
        UserResultDto userResultDto = userService.getUserByUserId(userId);
        log.debug("Result: {}", userResultDto);
        return userVoMapper.from(userResultDto);
    }
}
