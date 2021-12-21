package kpi.controllers;

import kpi.exception.AuthenticationException;
import kpi.exception.DuplicateEmailException;
import kpi.models.dto.request.UserRequestDto;
import kpi.models.dto.response.UserResponseDto;
import kpi.models.mapper.UserMapper;
import kpi.security.AuthenticationService;
import javax.validation.Valid;

import kpi.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(AuthenticationService authenticationService,
                                    UserService userService, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public @ResponseBody UserResponseDto addUser(@RequestBody @Valid UserRequestDto userRequestDto)
            throws AuthenticationException {
        if (userService.getByEmail(userRequestDto.getEmail()) != null) {
            throw new DuplicateEmailException("There ia already a registered user with this email");
        }
        return userMapper.getUserResponseDto(authenticationService
                .register(userRequestDto.getEmail(), userRequestDto.getPassword()));
    }

    @PostMapping(path = "/login")
    public @ResponseBody UserResponseDto getAuthUser(@RequestBody @Validated UserRequestDto userRequestDto)  {
        kpi.models.User loggedInUser = Objects.nonNull(userRequestDto)
                ? userService.getByEmail(userRequestDto.getEmail()) : null;
        if (loggedInUser != null && passwordEncoder.matches(userRequestDto.getPassword(),
                loggedInUser.getPassword())) {
            return userMapper.getUserResponseDto(loggedInUser);
        }
        throw new AuthenticationException("Wrong login or password");
    }

}
