package vu.psk.ugems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vu.psk.ugems.dto.CommentDTO;
import vu.psk.ugems.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentDTO createComment(CommentDTO commentDto) {
        return null;
    }

    public List<CommentDTO> getCommentsByTask(Long taskId) {
        return null;
    }

    public CommentDTO getComment(Long commentId) {
        return null;
    }

    public CommentDTO updateComment(CommentDTO commentDto) {
        return null;
    }

    public CommentDTO deleteComment(Long commentId) {
        return null;
    }
}
