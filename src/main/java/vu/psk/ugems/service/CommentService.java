package vu.psk.ugems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vu.psk.ugems.dto.CommentDTO;
import vu.psk.ugems.interceptor.LoggedAction;
import vu.psk.ugems.exception.ResourceNotFoundException;
import vu.psk.ugems.mapper.CommentMapper;
import vu.psk.ugems.repository.CommentRepository;
import vu.psk.ugems.repository.ProfileRepository;
import vu.psk.ugems.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@LoggedAction
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final TaskRepository taskRepository;
    private final ProfileRepository profileRepository;

    public CommentDTO createComment(CommentDTO commentDto) {
        var task = taskRepository.findById(commentDto.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + commentDto.getTaskId() + "not found"));
        var profile = profileRepository.findById(commentDto.getProfileId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile with ID " + commentDto.getProfileId() + "not found"));

        var comment = commentMapper.toEntity(commentDto);

        comment.setTask(task);
        comment.setProfile(profile);
        comment.setCreatedAt(LocalDateTime.now());

        return commentMapper.toDto(commentRepository.save(comment));
    }

    public List<CommentDTO> getCommentsByTask(Long taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + taskId + "not found"));
        var comments = task.getComments();
        return commentMapper.toDtoList(comments);
    }

    public CommentDTO getComment(Long commentId) {
        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with ID " + commentId + "not found"));
        return commentMapper.toDto(comment);
    }

    public CommentDTO updateComment(CommentDTO commentDto) {
        var commentToUpdate = commentRepository.findById(commentDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Comment with ID " + commentDto.getId() + "not found"));

        if (commentDto.getContent() != null) {
            commentToUpdate.setContent(commentDto.getContent());
        }

        return commentMapper.toDto(commentRepository.save(commentToUpdate));
    }

    public void deleteComment(Long commentId) {
        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with ID " + commentId + "not found"));
        commentRepository.delete(comment);
    }
}
