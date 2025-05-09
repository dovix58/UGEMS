package vu.psk.ugems.repositories;

import org.springframework.data.repository.CrudRepository;
import vu.psk.ugems.entities.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
