package vu.psk.ugems.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vu.psk.ugems.entity.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
