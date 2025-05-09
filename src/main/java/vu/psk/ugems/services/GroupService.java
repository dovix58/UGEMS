package vu.psk.ugems.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vu.psk.ugems.dtos.GroupDto;
import vu.psk.ugems.entities.Group;
import vu.psk.ugems.repositories.GroupRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;


    public GroupDto createGroup(GroupDto groupDto) {
        return null;
    }

    public List<GroupDto> getGroupsByUser(Long userId) {
        return null;
    }

    public GroupDto getGroup(Long groupId) {
        return null;
    }

    public GroupDto updateGroup(GroupDto groupDto) {
        return null;
    }

    public GroupDto deleteGroup(Long groupId) {
        return null;
    }
}
