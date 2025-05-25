package vu.psk.ugems.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import vu.psk.ugems.dto.InvitationRequest;
import vu.psk.ugems.dto.InvitationResponse;
import vu.psk.ugems.entity.Group;
import vu.psk.ugems.entity.Invitation;
import vu.psk.ugems.entity.Profile;
import vu.psk.ugems.entity.User;
import vu.psk.ugems.enums.InviteStatus;
import vu.psk.ugems.enums.ProfileRole;
import vu.psk.ugems.repository.GroupRepository;
import vu.psk.ugems.repository.InvitationRepository;
import vu.psk.ugems.repository.ProfileRepository;
import vu.psk.ugems.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class InvitationService {
    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ProfileRepository profileRepository;
    // private final InvitationMapper invitationMapper;

    @Transactional
    public void sendInvitation(InvitationRequest invitationRequest) {
        User inviter = userRepository.findById(invitationRequest.getInviterId())
                .orElseThrow(() -> new RuntimeException("Inviter not found"));
        Group group = groupRepository.findById(invitationRequest.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));
        User recipient = userRepository.findByEmail(invitationRequest.getInviteeEmail())
                .orElseThrow(() -> new RuntimeException("Recipient not found"));

        Invitation invitation = new Invitation();
        invitation.setInviterName(inviter.getFirstName());
        invitation.setGroup(group);
        invitation.setRecipient(recipient);
        invitation.setInviteStatus(InviteStatus.PENDING);

        invitationRepository.save(invitation);
    }

    @Transactional
    public void acceptInvitation(Long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new RuntimeException("Invitation not found"));

        if (invitation.getInviteStatus() != InviteStatus.PENDING) {
            throw new RuntimeException("Invitation already handled");
        }

        invitation.setInviteStatus(InviteStatus.ACCEPTED);
        invitationRepository.save(invitation);

        Profile profile = new Profile();

        var user = invitation.getRecipient();
        profile.setUser(user);
        profile.setJoinedDate(LocalDate.now());
        profile.setProfileRole(ProfileRole.MEMBER);
        profile.setUsername(user.getFirstName());

        var group = invitation.getGroup();
        profile.setGroup(group);

        profileRepository.save(profile);

    }

    @Transactional
    public void declineInvitation(Long invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new RuntimeException("Invitation not found"));

        if (invitation.getInviteStatus() != InviteStatus.PENDING) {
            throw new RuntimeException("Invitation already handled");
        }

        invitation.setInviteStatus(InviteStatus.DECLINED);
        invitationRepository.save(invitation);
    }

    public List<InvitationResponse> getAllInvitationsByUserId(Long userId) {

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Invitation> invitations = invitationRepository.findAllByRecipientId(userId);

        return invitations.stream()
                .filter(invitation -> invitation.getInviteStatus() == InviteStatus.PENDING)
                .map(invitation -> new InvitationResponse(
                        invitation.getId(),
                        invitation.getCreatedAt(),
                        invitation.getInviterName(),
                        invitation.getGroup().getName()
                ))
                .collect(Collectors.toList());
    }
}
