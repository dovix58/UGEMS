package vu.psk.ugems.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vu.psk.ugems.dto.TaskDTO;
import vu.psk.ugems.entity.Comment;
import vu.psk.ugems.entity.Task;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "group", ignore = true)
    @Mapping(target = "assignedTo", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "comments", ignore = true)
    Task toEntity(TaskDTO dto);

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "assignedTo.id", target = "assignedToId")
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "comments", target = "commentIds")
    TaskDTO toDto(Task task);

    default List<Long> mapCommentsToIds(List<Comment> comments) {
        if (comments == null) return null;
        return comments.stream().map(Comment::getId).toList();
    }
}


