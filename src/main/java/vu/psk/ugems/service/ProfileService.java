package vu.psk.ugems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vu.psk.ugems.dto.ProfileDto;
import vu.psk.ugems.repository.ProfileRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileDto createProfile(ProfileDto profileDto) {
        return null;
    }

    public List<ProfileDto> getProfilesByUser(Long userId) {
        return null;
    }

    public List<ProfileDto> getProfilesByGroup(Long groupId) {
        return null;
    }

    public ProfileDto getProfile(Long profileId) {
        return null;
    }

    public ProfileDto updateProfile(ProfileDto profileDto) {
        return null;
    }

    public ProfileDto deleteProfile(Long profileId) {
        return null;
    }
}
