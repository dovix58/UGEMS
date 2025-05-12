package vu.psk.ugems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vu.psk.ugems.dto.ProfileDTO;
import vu.psk.ugems.repository.ProfileRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileDTO createProfile(ProfileDTO profileDto) {
        return null;
    }

    public List<ProfileDTO> getProfilesByUser(Long userId) {
        return null;
    }

    public List<ProfileDTO> getProfilesByGroup(Long groupId) {
        return null;
    }

    public ProfileDTO getProfile(Long profileId) {
        return null;
    }

    public ProfileDTO updateProfile(ProfileDTO profileDto) {
        return null;
    }

    public ProfileDTO deleteProfile(Long profileId) {
        return null;
    }
}
