package vu.psk.ugems.mapper;

import org.mapstruct.Mapper;
import vu.psk.ugems.dto.ProfileDTO;
import vu.psk.ugems.entity.Profile;

@Mapper(componentModel = "spring", uses = {UserMapper.class, GroupMapper.class})
public interface ProfileMapper {
    ProfileDTO toDto(Profile profile);
    Profile toEntity(ProfileDTO dto);
}

