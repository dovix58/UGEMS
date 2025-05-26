package vu.psk.ugems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vu.psk.ugems.dto.InvitationRequest;
import vu.psk.ugems.dto.InvitationResponse;
import vu.psk.ugems.repository.UserRepository;
import vu.psk.ugems.service.InvitationService;

@RestController
@RequestMapping("/v1/invitations")
@RequiredArgsConstructor
public class InvitationController {
    private final InvitationService invitationService;
    private final UserRepository userRepository;

    @GetMapping("/invites/{userId}")
    public ResponseEntity<List<InvitationResponse>> getMyInvitations(@PathVariable Long userId) {
        return new ResponseEntity<>(invitationService.getAllInvitationsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/accept/{invitationId}")
    public void acceptInvitation(@PathVariable Long invitationId) {
         invitationService.acceptInvitation(invitationId);
    }

    @PostMapping("/decline/{invitationId}")
    public ResponseEntity<String> declineInvitation(@PathVariable Long invitationId) {
        invitationService.declineInvitation(invitationId);
        return ResponseEntity.ok("Invitation declined");
    }

    @PostMapping("/create")
    public void sendInvitation(@RequestBody InvitationRequest invitationRequest) {
        invitationService.sendInvitation(invitationRequest);
    }
}
