package vu.psk.ugems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private Long id;
    private String username;
    private String description;
    private String profileRole;
    private LocalDate joinedDate;
    private Long userId;
    private Long groupId;
}


