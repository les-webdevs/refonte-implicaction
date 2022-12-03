package com.dynonuggets.refonteimplicaction.adapter.forum;


import com.dynonuggets.refonteimplicaction.adapter.UserAdapter;
import com.dynonuggets.refonteimplicaction.dto.UserDto;
import com.dynonuggets.refonteimplicaction.dto.forum.ResponseDTO;
import com.dynonuggets.refonteimplicaction.model.User;
import com.dynonuggets.refonteimplicaction.model.forum.Response;
import com.dynonuggets.refonteimplicaction.model.forum.Topic;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ResponseAdapter {

    private final UserAdapter userAdapter;

    public Response toModel(ResponseDTO dto, User user){
        return Response.builder()
                .id(dto.getId())
                .message(dto.getMessage())
                .createdAt(dto.getCreated_at())
                .editedAt(dto.getEdited_at())
                .author(user)
                .build();
    }

    public ResponseDTO toDto(Response model){

        return ResponseDTO.builder()
                .id(model.getId())
                .message(model.getMessage())
                .created_at(model.getCreatedAt())
                .edited_at(model.getEditedAt())
                .author(userAdapter.toDto(model.getAuthor()))
                .build();
    }


}
