package kpi.controllers;

import kpi.exception.AuthenticationException;
import kpi.exception.DuplicateEmailException;
import kpi.models.dto.request.UserRegistrationDto;
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
    public @ResponseBody UserResponseDto addUser(@RequestBody @Valid UserRegistrationDto userRegistrationDto)
            throws AuthenticationException {
        if (userService.getByEmail(userRegistrationDto.getEmail()) != null) {
            throw new DuplicateEmailException("There ia already a registered user with this email");
        }
        return userMapper.getUserResponseDto(authenticationService
                .register(userRegistrationDto.getEmail(), userRegistrationDto.getPassword()));
    }

    @PostMapping(path = "/login")
    public @ResponseBody UserResponseDto getAuthUser(@RequestBody @Validated UserRequestDto userRequestDto)  {
        return userMapper.getUserResponseDto(authenticationService.login(userRequestDto.getEmail(),
                userRequestDto.getPassword()));
    }

}
