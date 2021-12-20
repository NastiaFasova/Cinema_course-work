package kpi.controllers;

import kpi.models.User;
import kpi.models.dto.response.UserResponseDto;
import kpi.models.mapper.UserMapper;
import kpi.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getByEmail(@RequestParam(name = "email") String email) {
        return userMapper.getUserResponseDto(userService.getByEmail(email));
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        List<User> users = userService.getAll();
        return users.stream()
                .map(userMapper::getUserResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/block/{id}")
    public UserResponseDto block(@PathVariable(value = "id") long id) {
        return userMapper.getUserResponseDto(userService.block(id));
    }

    @GetMapping("/unblock/{id}")
    public UserResponseDto unblock(@PathVariable(value = "id") long id) {
        return userMapper.getUserResponseDto(userService.unblock(id));
    }
}
