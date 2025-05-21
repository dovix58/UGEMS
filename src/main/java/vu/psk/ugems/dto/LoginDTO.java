package vu.psk.ugems.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
    private String token;
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
}
