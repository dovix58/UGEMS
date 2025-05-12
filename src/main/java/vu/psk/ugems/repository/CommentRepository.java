package vu.psk.ugems.repository;

import org.springframework.data.repository.CrudRepository;
import vu.psk.ugems.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
