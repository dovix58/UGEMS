package vu.psk.ugems.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vu.psk.ugems.entity.Group;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
}
