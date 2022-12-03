package com.dynonuggets.refonteimplicaction.dto.forum;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CategoryDTO {
    private Long id;
    private String title;
    private String description;
    private Long parentId;
    private List<CategoryDTO> children = new ArrayList<>();
}
