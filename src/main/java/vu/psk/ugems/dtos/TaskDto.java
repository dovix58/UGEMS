package vu.psk.ugems.dtos;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vu.psk.ugems.enums.Status;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private LocalDate createdDate;
    private LocalDate assignedDate;
    private String description;
    private LocalDate deadline;
    private String status;
    private Long groupId;
    private Long assigneeId;
    private Long creatorId;
}
