package vu.psk.ugems.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vu.psk.ugems.dto.ProfileDto;
import vu.psk.ugems.service.ProfileService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileDto> createComment(@RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.createProfile(profileDto), HttpStatus.CREATED);
    }

    @GetMapping("/of-user/userId?={userId}")
    public ResponseEntity<List<ProfileDto>> getProfilesByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(profileService.getProfilesByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/of-group/groupId?={groupId}")
    public ResponseEntity<List<ProfileDto>> getProfilesByGroup(@PathVariable Long groupId) {
        return new ResponseEntity<>(profileService.getProfilesByGroup(groupId), HttpStatus.OK);
    }

    @GetMapping("/profileId?={profileId}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long profileId) {
        return new ResponseEntity<>(profileService.getProfile(profileId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProfileDto> updateProfile(@RequestBody ProfileDto profileDto) {
        return new ResponseEntity<>(profileService.updateProfile(profileDto), HttpStatus.OK);
    }

    @DeleteMapping("/profileId?={profileId}")
    public ResponseEntity<ProfileDto> deleteProfile(@PathVariable Long profileId) {
        return new ResponseEntity<>(profileService.deleteProfile(profileId), HttpStatus.OK);
    }
}
