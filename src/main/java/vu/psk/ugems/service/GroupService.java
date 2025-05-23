package vu.psk.ugems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vu.psk.ugems.dto.GroupDTO;
import vu.psk.ugems.entity.Profile;
import vu.psk.ugems.exception.ResourceNotFoundException;
import vu.psk.ugems.mapper.GroupMapper;
import vu.psk.ugems.repository.GroupRepository;
import vu.psk.ugems.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final UserRepository userRepository;


    public GroupDTO createGroup(GroupDTO groupDto) {
        var group = groupMapper.toEntity(groupDto);

        group.setProfiles(null);
        group.setTasks(null);

        return groupMapper.toDto(groupRepository.save(group));
    }

    public List<GroupDTO> getGroupsByUser(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + "not found"));

        var groups = user.getProfiles().stream().map(Profile::getGroup).toList();
        return groupMapper.toDtoList(groups);
    }

    public GroupDTO getGroup(Long groupId) {
        var group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group with ID " + groupId + "not found"));
        return groupMapper.toDto(group);
    }

    public GroupDTO updateGroup(GroupDTO groupDto) {
        var group = groupRepository.findById(groupDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Group with ID " + groupDto.getId() + "not found"));

        if (groupDto.getName() != null) {
            group.setName(groupDto.getName());
        }

        return groupMapper.toDto(groupRepository.save(group));
    }

    public void deleteGroup(Long groupId) {
        var group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group with ID " + groupId + "not found"));
        groupRepository.delete(group);
    }
}
