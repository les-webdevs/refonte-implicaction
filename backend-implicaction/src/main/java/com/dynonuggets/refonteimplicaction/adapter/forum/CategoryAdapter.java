package com.dynonuggets.refonteimplicaction.adapter.forum;

import com.dynonuggets.refonteimplicaction.dto.forum.CategoryDTO;
import com.dynonuggets.refonteimplicaction.model.forum.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryAdapter {
    public Category toModel(CategoryDTO dto) {
        return Category.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    public CategoryDTO toDto(Category model) {
        CategoryDTO.CategoryDTOBuilder builder = CategoryDTO.builder()
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .children(new ArrayList<>());
        if (model.getParent() != null) {
            builder.parentId(model.getParent().getId());
        }
        return builder.build();
    }

    public CategoryDTO toDtoWithChildren(Category model) {
        CategoryDTO.CategoryDTOBuilder builder = CategoryDTO.builder()
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .children(new ArrayList<>());
        if (model.getParent() != null) {
            builder.parentId(model.getParent().getId());
        }
        if (model.getChildren().size() > 0) {
            List<CategoryDTO> children = model.getChildren().stream().map(this::toDtoWithChildren).collect(Collectors.toList());
            builder.children(children);
        }
        return builder.build();
    }
}
