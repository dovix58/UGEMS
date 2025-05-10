package vu.psk.ugems.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vu.psk.ugems.dto.TaskDto;
import vu.psk.ugems.entity.Task;
import vu.psk.ugems.enums.Status;
import vu.psk.ugems.repository.GroupRepository;
import vu.psk.ugems.repository.ProfileRepository;
import vu.psk.ugems.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final GroupRepository groupRepository;
    private final ProfileRepository profileRepository;

    public Task createTask(TaskDto taskDto) {
        var group = groupRepository.findById(taskDto.getGroupId())
                .orElseThrow(() -> new EntityNotFoundException("Group with ID " + taskDto.getGroupId() + "not found"));
        var creatorProfile = profileRepository.findById(taskDto.getCreatorId())
                .orElseThrow(() -> new EntityNotFoundException("Creator profile with ID " + taskDto.getCreatorId() + "not found"));

        var task = Task.builder()
                .title(taskDto.getTitle())
                .createdDate(LocalDate.now())
                .description(taskDto.getDescription())
                .deadline(taskDto.getDeadline())
                .status(Status.valueOf(taskDto.getStatus()))
                .group(group)
                .createdBy(creatorProfile)
                .build();

        if (taskDto.getAssigneeId() != null) {
            var assigneeProfile = profileRepository.findById(taskDto.getAssigneeId())
                    .orElseThrow(() -> new EntityNotFoundException("Assignee profile with ID " + taskDto.getAssigneeId() + "not found"));
            task.setAssignedTo(assigneeProfile);
            task.setAssignedDate(LocalDate.now());
        }

        taskRepository.save(task);
        return task;
    }

    public List<Task> getTasksByGroupId(Long groupId) {
        var group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group with ID " + groupId + "not found"));
        return group.getTasks();
    }

    public Task updateTask(TaskDto taskDto) {
        var group = groupRepository.findById(taskDto.getGroupId())
                .orElseThrow(() -> new EntityNotFoundException("Group with ID " + taskDto.getGroupId() + "not found"));
        var creatorProfile = profileRepository.findById(taskDto.getCreatorId())
                .orElseThrow(() -> new EntityNotFoundException("Creator profile with ID " + taskDto.getCreatorId() + "not found"));

        var task = Task.builder()
                .id(taskDto.getId())
                .title(taskDto.getTitle())
                .createdDate(LocalDate.now())
                .description(taskDto.getDescription())
                .deadline(taskDto.getDeadline())
                .status(Status.valueOf(taskDto.getStatus()))
                .group(group)
                .createdBy(creatorProfile)
                .build();

        if (taskDto.getAssigneeId() != null) {
            var assigneeProfile = profileRepository.findById(taskDto.getAssigneeId())
                    .orElseThrow(() -> new EntityNotFoundException("Assignee profile with ID " + taskDto.getAssigneeId() + "not found"));
            task.setAssignedTo(assigneeProfile);
            task.setAssignedDate(taskDto.getAssignedDate());
        }

        taskRepository.save(task);
        return task;
    }

    public Task deleteTask(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + id + "not found"));
        taskRepository.deleteById(id);
        return task;
    }
}
