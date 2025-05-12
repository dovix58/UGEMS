package vu.psk.ugems.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vu.psk.ugems.dto.TaskDTO;
import vu.psk.ugems.entity.Task;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GroupMapper.class, ProfileMapper.class, CommentMapper.class})
public interface TaskMapper {
    TaskDTO toDto(Task task);
    Task toEntity(TaskDTO dto);
    List<TaskDTO> toDtoList(List<Task> tasks);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "assignedDate", ignore = true)
    @Mapping(target = "deadline", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "assignedTo", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "group", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Task toEntity(TaskCreateDTO dto);
}

