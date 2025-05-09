package vu.psk.ugems.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vu.psk.ugems.entities.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
