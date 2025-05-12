package vu.psk.ugems.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vu.psk.ugems.dto.GroupDTO;
import vu.psk.ugems.service.GroupService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/groups")
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTO groupDto) {
        return new ResponseEntity<>(groupService.createGroup(groupDto), HttpStatus.CREATED);
    }

    @GetMapping("/of-user/userId?={userId}")
    public ResponseEntity<List<GroupDTO>> getGroupsByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(groupService.getGroupsByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/groupId?={groupId}")
    public ResponseEntity<GroupDTO> getGroup(@PathVariable Long groupId) {
        return new ResponseEntity<>(groupService.getGroup(groupId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<GroupDTO> updateGroup(@RequestBody GroupDTO groupDto) {
        return new ResponseEntity<>(groupService.updateGroup(groupDto), HttpStatus.OK);
    }

    @DeleteMapping("/groupId?={groupId}")
    public ResponseEntity<GroupDTO> deleteGroup(@PathVariable Long groupId) {
        return new ResponseEntity<>(groupService.deleteGroup(groupId), HttpStatus.OK);
    }
}
