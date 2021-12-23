package kpi.models.mapper;

import kpi.models.User;
import kpi.models.dto.request.UserRegistrationDto;
import kpi.models.dto.request.UserRequestDto;
import kpi.models.dto.response.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto getUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setFirstname(user.getFirstname());
        userResponseDto.setLastname(user.getLastname());
        userResponseDto.setAvatarUrl(user.getAvatarUrl());
        userResponseDto.setRole(user.getRoles().toString());
        userResponseDto.setBlocked(user.isBlocked());
        userResponseDto.setBill(user.getBill().getAmountOfMoney());
        return userResponseDto;
    }

    public User getUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        return user;
    }


    public User getUser(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());
        user.setFirstname(userRegistrationDto.getFirstname());
        user.setLastname(userRegistrationDto.getLastname());
        user.setAvatarUrl(userRegistrationDto.getAvatarUrl());
        user.setRoles(user.getRoles());
        user.setBlocked(user.isBlocked());
        return user;
    }
}
