package vu.psk.ugems.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vu.psk.ugems.dto.TaskDto;
import vu.psk.ugems.entity.Task;
import vu.psk.ugems.service.TaskService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.CREATED);
    }

    @GetMapping("/groupId?={groupId}")
    public ResponseEntity<List<Task>> getTasksByGroup(@PathVariable Long groupId) {
        return new ResponseEntity<>(taskService.getTasksByGroupId(groupId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.updateTask(taskDto), HttpStatus.OK);
    }

    @DeleteMapping("/taskId?={taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long taskId) {
        return new ResponseEntity<>(taskService.deleteTask(taskId), HttpStatus.OK);
    }
}
