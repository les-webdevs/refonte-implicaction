package com.dynonuggets.refonteimplicaction.adapter.forum;

import com.dynonuggets.refonteimplicaction.adapter.UserAdapter;
import com.dynonuggets.refonteimplicaction.dto.forum.TopicDto;
import com.dynonuggets.refonteimplicaction.model.User;
import com.dynonuggets.refonteimplicaction.model.forum.Topic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TopicAdapter {

    private final UserAdapter userAdapter;

    public Topic toModel(TopicDto dto, User user) {
        return Topic.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .message(dto.getMessage())
                .createdAt(dto.getCreatedAt())
                .editedAt(dto.getEditedAt())
                .isLocked(dto.isLocked())
                .isPinned(dto.isPinned())
                .author(user)
                .build();
    }

    public TopicDto toDto(Topic model) {
        return TopicDto.builder()
                .id(model.getId())
                .title(model.getTitle())
                .message(model.getMessage())
                .createdAt(model.getCreatedAt())
                .editedAt(model.getEditedAt())
                .isPinned(model.isPinned())
                .isLocked(model.isLocked())
                .author(userAdapter.toDto(model.getAuthor()))
                .build();
    }
}
