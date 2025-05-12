package vu.psk.ugems.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vu.psk.ugems.dto.ProfileDTO;
import vu.psk.ugems.entity.Profile;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "group", ignore = true)
    Profile toEntity(ProfileDTO dto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "group.id", target = "groupId")
    ProfileDTO toDto(Profile profile);
}


