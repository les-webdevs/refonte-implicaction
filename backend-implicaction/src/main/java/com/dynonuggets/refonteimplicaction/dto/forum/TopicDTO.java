package com.dynonuggets.refonteimplicaction.dto.forum;

import com.dynonuggets.refonteimplicaction.dto.UserDto;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TopicDTO {
    private long id;
    private String title;
    private String message;
    private Instant created_at;
    private Instant edited_at;
    private boolean is_locked;
    private boolean is_pinned;
    private UserDto author;
    private List<ResponseDTO> responses;
    private CategoryDTO category;
}
