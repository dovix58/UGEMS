package vu.psk.ugems.mapper;

import org.mapstruct.Mapper;
import vu.psk.ugems.dto.GroupDTO;
import vu.psk.ugems.entity.Group;

@Mapper(componentModel = "spring", uses = {ProfileMapper.class, TaskMapper.class})
public interface GroupMapper {
    GroupDTO toDto(Group group);
    Group toEntity(GroupDTO dto);
}

