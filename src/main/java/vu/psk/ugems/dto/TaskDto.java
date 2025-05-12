package vu.psk.ugems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private LocalDate createdDate;
    private LocalDate assignedDate;
    private String description;
    private LocalDate deadline;
    private String status;
    private Long groupId;
    private Long assignedToId;
    private Long createdById;
    private List<Long> commentIds;
}

