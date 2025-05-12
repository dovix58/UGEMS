package vu.psk.ugems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vu.psk.ugems.dto.GroupDTO;
import vu.psk.ugems.repository.GroupRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;


    public GroupDTO createGroup(GroupDTO groupDto) {
        return null;
    }

    public List<GroupDTO> getGroupsByUser(Long userId) {
        return null;
    }

    public GroupDTO getGroup(Long groupId) {
        return null;
    }

    public GroupDTO updateGroup(GroupDTO groupDto) {
        return null;
    }

    public GroupDTO deleteGroup(Long groupId) {
        return null;
    }
}
