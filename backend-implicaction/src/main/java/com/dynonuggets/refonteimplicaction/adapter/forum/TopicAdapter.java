package com.dynonuggets.refonteimplicaction.adapter.forum;

import com.dynonuggets.refonteimplicaction.adapter.UserAdapter;
import com.dynonuggets.refonteimplicaction.dto.forum.TopicDTO;
import com.dynonuggets.refonteimplicaction.model.User;
import com.dynonuggets.refonteimplicaction.model.forum.Topic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TopicAdapter {

    private final UserAdapter userAdapter;

    public Topic toModel(TopicDTO dto, User user) {
        return Topic.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .message(dto.getMessage())
                .createdAt(dto.getCreated_at())
                .editedAt(dto.getEdited_at())
                .isLocked(dto.is_locked())
                .isPinned(dto.is_pinned())
                .author(user)
                .build();
    }

    public TopicDTO toDto(Topic model) {
        return TopicDTO.builder()
                .id(model.getId())
                .title(model.getTitle())
                .message(model.getMessage())
                .created_at(model.getCreatedAt())
                .edited_at(model.getEditedAt())
                .is_pinned(model.isPinned())
                .is_locked(model.isLocked())
                .author(userAdapter.toDto(model.getAuthor()))
                .build();
    }
}
