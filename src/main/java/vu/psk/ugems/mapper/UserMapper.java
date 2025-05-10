package vu.psk.ugems.mapper;

import org.mapstruct.Mapper;
import vu.psk.ugems.dto.UserDTO;
import vu.psk.ugems.entity.User;

@Mapper(componentModel = "spring", uses = {ProfileMapper.class})
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO dto);
}

