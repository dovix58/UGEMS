package vu.psk.ugems.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vu.psk.ugems.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
