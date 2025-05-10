package vu.psk.ugems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vu.psk.ugems.dto.CommentDto;
import vu.psk.ugems.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentDto createComment(CommentDto commentDto) {
        return null;
    }

    public List<CommentDto> getCommentsByTask(Long taskId) {
        return null;
    }

    public CommentDto getComment(Long commentId) {
        return null;
    }

    public CommentDto updateComment(CommentDto commentDto) {
        return null;
    }

    public CommentDto deleteComment(Long commentId) {
        return null;
    }
}
