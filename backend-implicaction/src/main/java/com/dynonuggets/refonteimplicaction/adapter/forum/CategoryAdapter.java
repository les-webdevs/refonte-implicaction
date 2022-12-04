package com.dynonuggets.refonteimplicaction.adapter.forum;

import com.dynonuggets.refonteimplicaction.dto.forum.CategoryDto;
import com.dynonuggets.refonteimplicaction.model.forum.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryAdapter {
    public Category toModel(CategoryDto dto) {
        return Category.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    public CategoryDto toDto(Category model) {
        CategoryDto.CategoryDtoBuilder builder = CategoryDto.builder()
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .children(new ArrayList<>());
        if (model.getParent() != null) {
            builder.parentId(model.getParent().getId());
        }
        return builder.build();
    }

    public CategoryDto toDtoWithChildren(Category model) {
        CategoryDto.CategoryDtoBuilder builder = CategoryDto.builder()
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .children(new ArrayList<>());
        if (model.getParent() != null) {
            builder.parentId(model.getParent().getId());
        }
        if (model.getChildren().size() > 0) {
            List<CategoryDto> children = model.getChildren().stream()
                    .map(this::toDtoWithChildren)
                    .collect(Collectors.toList());
            builder.children(children);
        }
        return builder.build();
    }
}
