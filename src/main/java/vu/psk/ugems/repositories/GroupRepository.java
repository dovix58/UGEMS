package vu.psk.ugems.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vu.psk.ugems.entities.Group;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
}
