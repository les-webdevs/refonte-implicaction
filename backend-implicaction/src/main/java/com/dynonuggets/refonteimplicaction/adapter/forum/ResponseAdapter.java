package com.dynonuggets.refonteimplicaction.adapter.forum;

import com.dynonuggets.refonteimplicaction.adapter.UserAdapter;
import com.dynonuggets.refonteimplicaction.dto.forum.ResponseDto;
import com.dynonuggets.refonteimplicaction.model.User;
import com.dynonuggets.refonteimplicaction.model.forum.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ResponseAdapter {

    private final UserAdapter userAdapter;

    public Response toModel(ResponseDto dto, User user) {
        return Response.builder()
                .id(dto.getId())
                .message(dto.getMessage())
                .createdAt(dto.getCreated_at())
                .editedAt(dto.getEdited_at())
                .author(user)
                .build();
    }

    public ResponseDto toDto(Response model) {

        return ResponseDto.builder()
                .id(model.getId())
                .message(model.getMessage())
                .created_at(model.getCreatedAt())
                .edited_at(model.getEditedAt())
                .author(userAdapter.toDto(model.getAuthor()))
                .build();
    }


}
