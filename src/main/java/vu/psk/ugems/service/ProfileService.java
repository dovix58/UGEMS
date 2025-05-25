package vu.psk.ugems.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vu.psk.ugems.dto.ProfileDTO;
import vu.psk.ugems.interceptor.LoggedAction;
import vu.psk.ugems.exception.ResourceNotFoundException;
import vu.psk.ugems.mapper.ProfileMapper;
import vu.psk.ugems.repository.GroupRepository;
import vu.psk.ugems.repository.ProfileRepository;
import vu.psk.ugems.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@LoggedAction
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public ProfileDTO createProfile(ProfileDTO profileDto) {

        var group = groupRepository.findById(profileDto.getGroupId())
                .orElseThrow(() -> new ResourceNotFoundException("Group with ID " + profileDto.getGroupId() + "not found"));
        var user = userRepository.findById(profileDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + profileDto.getUserId() + "not found"));

        var profile = profileMapper.toEntity(profileDto);

        profile.setGroup(group);
        profile.setUser(user);
        profile.setJoinedDate(LocalDate.now());

        return profileMapper.toDto(profileRepository.save(profile));
    }

    public List<ProfileDTO> getProfilesByUser(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + "not found"));

        var profiles = user.getProfiles();
        return profileMapper.toDtoList(profiles);
    }

    public List<ProfileDTO> getProfilesByGroup(Long groupId) {
        var group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group with ID " + groupId + "not found"));
        var profiles = group.getProfiles();
        return profileMapper.toDtoList(profiles);
    }

    public ProfileDTO getProfile(Long profileId) {
        var profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile with ID " + profileId + "not found"));
        return profileMapper.toDto(profile);
    }

    public ProfileDTO updateProfile(ProfileDTO profileDto) {
        var profileToUpdate = profileRepository.findById(profileDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile with ID " + profileDto.getId() + "not found"));

        if (profileDto.getUsername() != null) {
            profileToUpdate.setUsername(profileDto.getUsername());
        }
        if (profileDto.getDescription() != null) {
            profileToUpdate.setDescription(profileDto.getDescription());
        }
        if (profileDto.getRole() != null) {
            profileToUpdate.setRole(profileDto.getRole());
        }

        return profileMapper.toDto(profileRepository.save(profileToUpdate));
    }

    public void deleteProfile(Long profileId) {
        var profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile with ID " + profileId + "not found"));
        profileRepository.delete(profile);
    }
}
