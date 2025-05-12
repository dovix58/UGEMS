package vu.psk.ugems.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vu.psk.ugems.dto.CommentDTO;
import vu.psk.ugems.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "task", ignore = true)
    @Mapping(target = "profile", ignore = true)
    Comment toEntity(CommentDTO dto);

    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "profile.id", target = "profileId")
    CommentDTO toDto(Comment entity);
}



