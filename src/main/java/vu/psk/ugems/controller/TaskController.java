package vu.psk.ugems.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;
import vu.psk.ugems.dto.TaskDTO;
import vu.psk.ugems.service.TaskService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDto) {
        return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.CREATED);
    }

    @GetMapping("/of-group/{groupId}")
    public ResponseEntity<List<TaskDTO>> getTasksByGroup(@PathVariable Long groupId) {
        return new ResponseEntity<>(taskService.getTasksByGroupId(groupId), HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long taskId) {
        return new ResponseEntity<>(taskService.getTask(taskId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDto) {
        try {
            return new ResponseEntity<>(taskService.updateTask(taskDto), HttpStatus.OK);
        } catch (ObjectOptimisticLockingFailureException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
