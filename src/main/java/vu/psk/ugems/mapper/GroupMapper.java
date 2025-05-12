package vu.psk.ugems.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vu.psk.ugems.dto.GroupDTO;
import vu.psk.ugems.entity.Group;
import vu.psk.ugems.entity.Profile;
import vu.psk.ugems.entity.Task;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "profiles", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    Group toEntity(GroupDTO dto);

    @Mapping(source = "profiles", target = "profileIds")
    @Mapping(source = "tasks", target = "taskIds")
    GroupDTO toDto(Group group);

    List<GroupDTO> toDtoList(List<Group> groups);

    //mapstruct uses these automatically:
    default List<Long> mapProfilesToIds(List<Profile> profiles) {
        if (profiles == null) return null;
        return profiles.stream().map(Profile::getId).toList();
    }

    default List<Long> mapTasksToIds(List<Task> tasks) {
        if (tasks == null) return null;
        return tasks.stream().map(Task::getId).toList();
    }
}