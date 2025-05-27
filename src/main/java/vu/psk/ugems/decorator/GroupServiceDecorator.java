package vu.psk.ugems.decorator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import vu.psk.ugems.dto.CreateGroupRequest;
import vu.psk.ugems.dto.GroupDTO;
import vu.psk.ugems.exception.AccessDeniedException;
import vu.psk.ugems.interceptor.LoggedAction;
import vu.psk.ugems.service.GroupService;
import vu.psk.ugems.service.IGroupService;

import java.util.List;

@Service
@LoggedAction
@Primary
@ConditionalOnProperty(name = "business.decorator.enabled", havingValue = "true")
public class GroupServiceDecorator implements IGroupService {
    private final GroupService delegate;

    public GroupServiceDecorator(@Qualifier("baseGroupService") GroupService delegate) {
        this.delegate = delegate;
    }

    @Override
    public GroupDTO createGroup(CreateGroupRequest createGroupRequest) {
        return delegate.createGroup(createGroupRequest);
    }

    @Override
    public List<GroupDTO> getGroupsByUser(Long userId) {
        return delegate.getGroupsByUser(userId);
    }

    @Override
    public GroupDTO getGroup(Long groupId) {
        if (delegate.groupIsOwnedByCurrentUser(groupId) || delegate.currentUserHasAccessToGroup(groupId))
            return delegate.getGroup(groupId);
        else
            throw new AccessDeniedException("User does not have access to this group");
    }

    @Override
    public GroupDTO updateGroup(GroupDTO groupDto) {
        if (delegate.groupIsOwnedByCurrentUser(groupDto.getId()))
            return delegate.updateGroup(groupDto);
        else
            throw new AccessDeniedException("User does not have access to this group");
    }

    @Override
    public void deleteGroup(Long groupId) {
        if (delegate.groupIsOwnedByCurrentUser(groupId))
            delegate.deleteGroup(groupId);
        else
            throw new AccessDeniedException("User does not have access to this group");
    }

    @Override
    public boolean groupIsOwnedByCurrentUser(Long groupId) {
        return delegate.groupIsOwnedByCurrentUser(groupId);
    }

    @Override
    public boolean currentUserHasAccessToGroup(Long groupId) {
        return delegate.currentUserHasAccessToGroup(groupId);
    }
}
