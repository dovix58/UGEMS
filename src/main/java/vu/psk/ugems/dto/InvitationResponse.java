package vu.psk.ugems.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vu.psk.ugems.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvitationResponse {
    private Long id;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String inviterName;
    private String groupName;
}
