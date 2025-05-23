package vu.psk.ugems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import vu.psk.ugems.entity.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation,Long> {
    List<Invitation> findAllByRecipientId(Long recipientId);
}
