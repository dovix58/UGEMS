package vu.psk.ugems.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import vu.psk.ugems.dto.CreateGroupRequest;
import vu.psk.ugems.dto.GroupDTO;
import vu.psk.ugems.entity.Group;
import vu.psk.ugems.entity.Profile;
import vu.psk.ugems.enums.ProfileRole;
import vu.psk.ugems.mapper.GroupMapper;
import vu.psk.ugems.repository.GroupRepository;
import vu.psk.ugems.repository.ProfileRepository;
import vu.psk.ugems.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;


    public GroupDTO createGroup(CreateGroupRequest createGroupRequest) {
        var user = userRepository.findById(createGroupRequest.getCreatorId())
                .orElseThrow(EntityNotFoundException::new);

        Group group = new Group();
        group.setName(createGroupRequest.getGroupName());
        group.setTasks(null);
        group.setInvitations(null);

        Group savedGroup = groupRepository.save(group);

        var profile = new Profile();
        profile.setUser(user);
        profile.setJoinedDate(LocalDate.now());
        profile.setProfileRole(ProfileRole.OWNER);
        profile.setUsername(user.getFirstName());
        profile.setGroup(savedGroup);

        profileRepository.save(profile);

        return groupMapper.toDto(savedGroup);
    }

    public List<GroupDTO> getGroupsByUser(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + "not found"));

        var groups = user.getProfiles().stream().map(Profile::getGroup).toList();
        return groupMapper.toDtoList(groups);
    }

    public GroupDTO getGroup(Long groupId) {
        var group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group with ID " + groupId + "not found"));
        return groupMapper.toDto(group);
    }

    public GroupDTO updateGroup(GroupDTO groupDto) {
        var group = groupRepository.findById(groupDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Group with ID " + groupDto.getId() + "not found"));

        if (groupDto.getName() != null) {
            group.setName(groupDto.getName());
        }

        return groupMapper.toDto(groupRepository.save(group));
    }

    public void deleteGroup(Long groupId) {
        var group = groupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("Group with ID " + groupId + "not found"));
        groupRepository.delete(group);
    }
}
