package vu.psk.ugems.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vu.psk.ugems.dto.CommentDTO;
import vu.psk.ugems.service.CommentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDto) {
        return new ResponseEntity<>(commentService.createComment(commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/of-task/{taskId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByTask(@PathVariable Long taskId) {
        return new ResponseEntity<>(commentService.getCommentsByTask(taskId), HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.getComment(commentId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDto) {
        return new ResponseEntity<>(commentService.updateComment(commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
