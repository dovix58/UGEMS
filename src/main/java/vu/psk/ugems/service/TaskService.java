package vu.psk.ugems.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vu.psk.ugems.dto.TaskDTO;
import vu.psk.ugems.entity.Task;
import vu.psk.ugems.enums.Status;
import vu.psk.ugems.interceptor.LoggedAction;
import vu.psk.ugems.mapper.TaskMapper;
import vu.psk.ugems.repository.GroupRepository;
import vu.psk.ugems.repository.ProfileRepository;
import vu.psk.ugems.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@LoggedAction
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final GroupRepository groupRepository;
    private final ProfileRepository profileRepository;
    private final TaskMapper taskMapper;

    public TaskDTO createTask(TaskDTO taskDto) {

        var task = taskMapper.toEntity(taskDto);

        var group = groupRepository.findById(taskDto.getGroupId())
                .orElseThrow(() -> new EntityNotFoundException("Group with ID " + taskDto.getGroupId() + "not found"));
        var creatorProfile = profileRepository.findById(taskDto.getCreatedById())
                .orElseThrow(() -> new EntityNotFoundException("Creator profile with ID " + taskDto.getCreatedById() + "not found"));
        if(taskDto.getAssignedToId() != null){
            var assignedTo = profileRepository.findById(taskDto.getAssignedToId())
                    .orElseThrow(() -> new EntityNotFoundException("Assigned to with ID " + taskDto.getAssignedToId() + "not found"));

            task.setAssignedTo(assignedTo);
            task.setAssignedDate(LocalDate.now());
        } else {
            task.setAssignedTo(null);
            task.setAssignedDate(null);
        }

        if (taskDto.getDeadline() != null) {
            task.setDeadline(taskDto.getDeadline());
        } else {
            task.setDeadline(null);
        }

        task.setCreatedDate(LocalDate.now());
        task.setCreatedBy(creatorProfile);
        task.setStatus(Status.TO_DO);
        task.setGroup(group);
        task.setComments(null);

        return taskMapper.toDto(taskRepository.save(task));
    }

    public List<TaskDTO> getTasksByGroupId(Long groupId) {
        var group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group with ID " + groupId + "not found"));
        var tasks = group.getTasks();
        return taskMapper.toDtoList(tasks);
    }

    public TaskDTO getTask(Long taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + taskId + "not found"));
        return taskMapper.toDto(task);
    }

    public TaskDTO updateTask(TaskDTO taskDto) {

        var taskToUpdate = taskRepository.findById(taskDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + taskDto.getId() + " not found"));

        if (taskDto.getTitle() != null) {
            taskToUpdate.setTitle(taskDto.getTitle());
        }
        if (taskDto.getDescription() != null) {
            taskToUpdate.setDescription(taskDto.getDescription());
        }
        if (taskDto.getDeadline() != null) {
            taskToUpdate.setDeadline(taskDto.getDeadline());
        }
        if (taskDto.getStatus() != null) {
            taskToUpdate.setStatus(Status.valueOf(taskDto.getStatus()));
        }


        if (taskDto.getAssignedToId() != null) {
            var assigneeProfile = profileRepository.findById(taskDto.getAssignedToId())
                    .orElseThrow(() -> new EntityNotFoundException("Assignee profile with ID " + taskDto.getAssignedToId() + " not found"));
            taskToUpdate.setAssignedTo(assigneeProfile);
            taskToUpdate.setAssignedDate(LocalDate.now());
        } else {
            taskToUpdate.setAssignedTo(null);
            taskToUpdate.setAssignedDate(null);
        }

        return taskMapper.toDto(taskRepository.save(taskToUpdate));
    }

    public void deleteTask(Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + id + "not found"));
        taskRepository.deleteById(id);
    }
}
