package vu.psk.ugems.mapper;

import org.mapstruct.Mapper;
import vu.psk.ugems.dto.TaskDTO;
import vu.psk.ugems.entity.Task;

@Mapper(componentModel = "spring", uses = {GroupMapper.class, ProfileMapper.class, CommentMapper.class})
public interface TaskMapper {
    TaskDTO toDto(Task task);
    Task toEntity(TaskDTO dto);
}

