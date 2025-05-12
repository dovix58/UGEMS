package vu.psk.ugems.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vu.psk.ugems.dto.UserDTO;
import vu.psk.ugems.entity.Profile;
import vu.psk.ugems.entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "profiles", ignore = true)
    User toEntity(UserDTO dto);

    @Mapping(source = "profiles", target = "profileIds")
    UserDTO toDto(User user);

    default List<Long> mapProfilesToIds(List<Profile> profiles) {
        if (profiles == null) return null;
        return profiles.stream().map(Profile::getId).toList();
    }
}