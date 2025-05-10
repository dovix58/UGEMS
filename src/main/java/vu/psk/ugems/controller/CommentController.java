package vu.psk.ugems.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vu.psk.ugems.dto.CommentDto;
import vu.psk.ugems.service.CommentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/of-task/taskId?={taskId}")
    public ResponseEntity<List<CommentDto>> getCommentsByTask(@PathVariable Long taskId) {
        return new ResponseEntity<>(commentService.getCommentsByTask(taskId), HttpStatus.OK);
    }

    @GetMapping("/commentId?={commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.getComment(commentId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.updateComment(commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/commentId?={commentId}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.deleteComment(commentId), HttpStatus.OK);
    }
}
