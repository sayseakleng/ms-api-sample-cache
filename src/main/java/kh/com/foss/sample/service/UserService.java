package kh.com.foss.sample.service;

import java.util.List;
import kh.com.foss.sample.dto.UserCreationInputDto;
import kh.com.foss.sample.dto.UserResultDto;
import kh.com.foss.sample.dto.UserUpdateInputDto;

public interface UserService {
    UserResultDto register(UserCreationInputDto userCreationInputDto);

    UserResultDto update(UserUpdateInputDto userUpdateInputDto);

    UserResultDto getUserByUserId(Long userId);

    List<UserResultDto> getList();
}
