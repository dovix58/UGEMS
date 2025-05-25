package vu.psk.ugems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vu.psk.ugems.entity.Invitation;
import vu.psk.ugems.enums.InviteStatus;

public interface InvitationRepository extends JpaRepository<Invitation,Long> {
    List<Invitation> findAllByRecipientId(Long recipientId);
    boolean existsByRecipientIdAndGroupIdAndInviteStatus(Long recipientId, Long groupId, InviteStatus status);

}
