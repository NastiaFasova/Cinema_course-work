package kpi.controllers;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kpi.exception.AuthenticationException;
import kpi.exception.DuplicateEmailException;
import kpi.models.dto.request.UserRegistrationDto;
import kpi.models.dto.request.UserRequestDto;
import kpi.models.dto.response.UserResponseDto;
import kpi.models.mapper.UserMapper;
import kpi.security.AuthenticationService;
import javax.validation.Valid;

import kpi.security.JwtTokenRepository;
import kpi.service.UserService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@RestController
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final UserMapper userMapper;

    public AuthenticationController(AuthenticationService authenticationService,
                                    UserService userService, UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    @CrossOrigin
    public @ResponseBody UserResponseDto addUser(@RequestBody @Valid UserRegistrationDto userRegistrationDto)
            throws AuthenticationException {
        if (userService.getByEmail(userRegistrationDto.getEmail()) != null) {
            throw new DuplicateEmailException("There ia already a registered user with this email");
        }
        return userMapper.getUserResponseDto(authenticationService
                .register(userMapper.getUser(userRegistrationDto)));
    }

    @PostMapping(path = "/login")
    @CrossOrigin
    public @ResponseBody UserResponseDto getAuthUser(@RequestBody @Validated UserRequestDto userRequestDto)  {
        UserResponseDto responseDto = userMapper.getUserResponseDto(authenticationService.login(userRequestDto.getEmail(),
                userRequestDto.getPassword()));
        responseDto.setToken(generateToken());
        return responseDto;
    }

    private CsrfToken generateToken() {
        String id = UUID.randomUUID().toString().replace("-", "");
        Date now = new Date();
        Date exp = Date.from(LocalDateTime.now().plusDays(3)
                .atZone(ZoneId.systemDefault()).toInstant());

        String token = "";
        try {
            token = Jwts.builder()
                    .setId(id)
                    .setIssuedAt(now)
                    .setNotBefore(now)
                    .setExpiration(exp)
                    .signWith(SignatureAlgorithm.HS256, "secret")
                    .compact();
        } catch (JwtException e) {
            e.printStackTrace();
        }
        return new DefaultCsrfToken("x-csrf-token", "_csrf", token);
    }

}
