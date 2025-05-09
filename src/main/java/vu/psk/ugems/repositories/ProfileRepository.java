package vu.psk.ugems.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vu.psk.ugems.entities.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
