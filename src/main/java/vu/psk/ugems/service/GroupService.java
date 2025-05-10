package vu.psk.ugems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vu.psk.ugems.dto.GroupDto;
import vu.psk.ugems.repository.GroupRepository;

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
