package vu.psk.ugems.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import vu.psk.ugems.dto.CreateGroupRequest;
import vu.psk.ugems.dto.GroupDTO;
import vu.psk.ugems.service.GroupService;
import vu.psk.ugems.service.IGroupService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/groups")
public class GroupController {
    private final IGroupService groupService;

    @PostMapping
    public ResponseEntity<GroupDTO> createGroup(@RequestBody CreateGroupRequest createGroupRequest) {
        return new ResponseEntity<>(groupService.createGroup(createGroupRequest), HttpStatus.CREATED);
    }

    @GetMapping("/of-user/{userId}")
    public ResponseEntity<List<GroupDTO>> getGroupsByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(groupService.getGroupsByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/{groupId}")
//    @PreAuthorize("@groupService.groupIsOwnedByCurrentUser(#groupId) or @groupService.currentUserHasAccessToGroup(#groupId)")
    public ResponseEntity<GroupDTO> getGroup(@PathVariable Long groupId) {
        return new ResponseEntity<>(groupService.getGroup(groupId), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("@groupService.groupIsOwnedByCurrentUser(#groupDto.id)")
    public ResponseEntity<GroupDTO> updateGroup(@RequestBody GroupDTO groupDto) {
        return new ResponseEntity<>(groupService.updateGroup(groupDto), HttpStatus.OK);
    }

    @DeleteMapping("/{groupId}")
    @PreAuthorize("@groupService.groupIsOwnedByCurrentUser(#groupId)")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
