package kh.com.foss.sample.service.impl;

import java.util.List;
import kh.com.foss.sample.dao.UserDao;
import kh.com.foss.sample.dto.UserCreationInputDto;
import kh.com.foss.sample.dto.UserResultDto;
import kh.com.foss.sample.dto.UserUpdateInputDto;
import kh.com.foss.sample.entity.User;
import kh.com.foss.sample.mapper.UserMapper;
import kh.com.foss.sample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDAO;
    private final UserMapper userMapper;

    @Override
    public UserResultDto register(UserCreationInputDto userCreationInputDto) {
        User user = userMapper.from(userCreationInputDto);
        User save = userDAO.save(user);
        return userMapper.to(save);
    }

    @Override
    public UserResultDto update(UserUpdateInputDto userUpdateInputDto) {
        User userRecord = userDAO.getReferenceById(userUpdateInputDto.getUserId());
        userMapper.copy(userUpdateInputDto, userRecord);
        User save = userDAO.save(userRecord);
        return userMapper.to(save);
    }

    @Override
    @Cacheable(value = "userCache", key = "#userId")
    public UserResultDto getUserByUserId(Long userId) {
        User user = userDAO.getReferenceById(userId);
        return userMapper.to(user);
    }

    @Override
    public List<UserResultDto> getList() {
        List<User> findAll = userDAO.findAll();
        return userMapper.to(findAll);
    }
}
