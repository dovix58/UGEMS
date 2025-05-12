package vu.psk.ugems.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vu.psk.ugems.entity.Group;
import vu.psk.ugems.entity.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
