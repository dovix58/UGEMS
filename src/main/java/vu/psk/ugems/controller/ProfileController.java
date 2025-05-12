package vu.psk.ugems.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vu.psk.ugems.dto.ProfileDTO;
import vu.psk.ugems.service.ProfileService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDto) {
        return new ResponseEntity<>(profileService.createProfile(profileDto), HttpStatus.CREATED);
    }

    @GetMapping("/of-user/{userId}")
    public ResponseEntity<List<ProfileDTO>> getProfilesByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(profileService.getProfilesByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/of-group/{groupId}")
    public ResponseEntity<List<ProfileDTO>> getProfilesByGroup(@PathVariable Long groupId) {
        return new ResponseEntity<>(profileService.getProfilesByGroup(groupId), HttpStatus.OK);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long profileId) {
        return new ResponseEntity<>(profileService.getProfile(profileId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDto) {
        return new ResponseEntity<>(profileService.updateProfile(profileDto), HttpStatus.OK);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long profileId) {
        profileService.deleteProfile(profileId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
