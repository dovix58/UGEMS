package vu.psk.ugems.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import vu.psk.ugems.dto.ProfileDTO;
import vu.psk.ugems.entity.Profile;
import vu.psk.ugems.enums.ProfileRole;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "profileRole", target = "profileRole", qualifiedByName = "profileRoleToString")
    ProfileDTO toDto(Profile profile);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "group", ignore = true)
    @Mapping(source = "profileRole", target = "profileRole", qualifiedByName = "stringToProfileRole")
    Profile toEntity(ProfileDTO dto);

    List<ProfileDTO> toDtoList(List<Profile> profiles);

    @Named("profileRoleToString")
    default String profileRoleToString(ProfileRole profileRole) {
        return profileRole == null ? null : profileRole.name(); // or getLabel() if you prefer
    }

    @Named("stringToProfileRole")
    default ProfileRole stringToProfileRole(String role) {
        return role == null ? null : ProfileRole.valueOf(role);
    }
}


