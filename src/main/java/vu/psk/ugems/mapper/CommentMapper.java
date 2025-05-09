package vu.psk.ugems.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vu.psk.ugems.dto.CommentDTO;
import vu.psk.ugems.entity.Comment;

@Mapper(componentModel = "spring", uses = {TaskMapper.class, ProfileMapper.class})
public interface CommentMapper {
    CommentDTO toDto(Comment comment);
    Comment toEntity(CommentDTO dto);
}


