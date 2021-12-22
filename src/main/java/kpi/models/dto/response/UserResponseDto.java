package kpi.models.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.web.csrf.CsrfToken;

@Setter
@Getter
public class UserResponseDto {
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String avatarUrl;
    private String role;
    private boolean blocked;
    private CsrfToken token;
    private Double bill;
}
