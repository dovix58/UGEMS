package vu.psk.ugems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import vu.psk.ugems.dto.CreateGroupRequest;
import vu.psk.ugems.dto.GroupDTO;
import vu.psk.ugems.entity.Group;
import vu.psk.ugems.entity.Profile;
import vu.psk.ugems.entity.UserPrincipal;
import vu.psk.ugems.interceptor.LoggedAction;
import vu.psk.ugems.exception.ResourceNotFoundException;
import vu.psk.ugems.enums.ProfileRole;
import vu.psk.ugems.mapper.GroupMapper;
import vu.psk.ugems.repository.GroupRepository;
import vu.psk.ugems.repository.ProfileRepository;
import vu.psk.ugems.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service("baseGroupService")
@LoggedAction
@RequiredArgsConstructor
public class GroupService implements IGroupService {

    private final GroupRepository groupRepository;
    private final ProfileRepository profileRepository;
    private final GroupMapper groupMapper;
    private final UserRepository userRepository;

    @Override
    public GroupDTO createGroup(CreateGroupRequest createGroupRequest) {
        var user = userRepository.findById(createGroupRequest.getCreatorId())
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + createGroupRequest.getCreatorId() + "not found"));

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

    @Override
    public List<GroupDTO> getGroupsByUser(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + "not found"));

        var groups = user.getProfiles().stream().map(Profile::getGroup).toList();
        return groupMapper.toDtoList(groups);
    }

    @Override
    public GroupDTO getGroup(Long groupId) {
        var group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group with ID " + groupId + "not found"));
        return groupMapper.toDto(group);
    }

    @Override
    public GroupDTO updateGroup(GroupDTO groupDto) {
        var group = groupRepository.findById(groupDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Group with ID " + groupDto.getId() + "not found"));

        if (groupDto.getName() != null) {
            group.setName(groupDto.getName());
        }

        return groupMapper.toDto(groupRepository.save(group));
    }

    @Override
    public void deleteGroup(Long groupId) {
        var group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group with ID " + groupId + "not found"));
        groupRepository.delete(group);
    }

    @Override
    public boolean groupIsOwnedByCurrentUser(Long groupId) {
        var userPrincipal = (UserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        var profile = profileRepository.findByUserIdAndGroupId(userPrincipal.getUser().getId(), groupId);
        return profile.getProfileRole() == ProfileRole.OWNER;
    }

    @Override
    public boolean currentUserHasAccessToGroup(Long groupId) {
        var userPrincipal = (UserPrincipal) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        var profile = profileRepository.findByUserIdAndGroupId(userPrincipal.getUser().getId(), groupId);
        return profile != null;
    }
}
