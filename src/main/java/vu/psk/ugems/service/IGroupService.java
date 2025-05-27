package vu.psk.ugems.service;

import vu.psk.ugems.dto.CreateGroupRequest;
import vu.psk.ugems.dto.GroupDTO;

import java.util.List;

public interface IGroupService {
    GroupDTO createGroup(CreateGroupRequest createGroupRequest);
    List<GroupDTO> getGroupsByUser(Long userId);
    GroupDTO getGroup(Long groupId);
    GroupDTO updateGroup(GroupDTO groupDto);
    void deleteGroup(Long groupId);
    boolean groupIsOwnedByCurrentUser(Long groupId);
    boolean currentUserHasAccessToGroup(Long groupId);
}
