package vu.psk.ugems.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vu.psk.ugems.dto.GroupDto;
import vu.psk.ugems.service.GroupService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/groups")
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
        return new ResponseEntity<>(groupService.createGroup(groupDto), HttpStatus.CREATED);
    }

    @GetMapping("/of-user/userId?={userId}")
    public ResponseEntity<List<GroupDto>> getGroupsByUser(@PathVariable Long userId) {
        return new ResponseEntity<>(groupService.getGroupsByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/groupId?={groupId}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long groupId) {
        return new ResponseEntity<>(groupService.getGroup(groupId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) {
        return new ResponseEntity<>(groupService.updateGroup(groupDto), HttpStatus.OK);
    }

    @DeleteMapping("/groupId?={groupId}")
    public ResponseEntity<GroupDto> deleteGroup(@PathVariable Long groupId) {
        return new ResponseEntity<>(groupService.deleteGroup(groupId), HttpStatus.OK);
    }
}
