package com.dynonuggets.refonteimplicaction.service.forum;

import com.dynonuggets.refonteimplicaction.adapter.forum.CategoryAdapter;
import com.dynonuggets.refonteimplicaction.dto.forum.CategoryDTO;
import com.dynonuggets.refonteimplicaction.exception.ImplicactionException;
import com.dynonuggets.refonteimplicaction.exception.NotFoundException;
import com.dynonuggets.refonteimplicaction.model.forum.Category;
import com.dynonuggets.refonteimplicaction.repository.forum.CategoryRepository;
import com.dynonuggets.refonteimplicaction.repository.forum.ResponseRepository;
import com.dynonuggets.refonteimplicaction.repository.forum.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.dynonuggets.refonteimplicaction.utils.Message.CATEGORY_NOT_FOUND_MESSAGE;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final TopicRepository topicRepository;
    private final ResponseRepository responseRepository;
    private final CategoryAdapter categoryAdapter;

    @Transactional
    public List<CategoryDTO> getCategories() {
        Map<Long, CategoryDTO> categoryMap = new HashMap<>();
        return categoryRepository.findAll().stream()
                .map(categoryAdapter::toDto)
                .peek(categoryDTO -> categoryMap.put(categoryDTO.getId(), categoryDTO))
                .peek(categoryDTO -> {
                    CategoryDTO parentCategory = categoryMap.get(categoryDTO.getParentId());
                    if (parentCategory == null) {
                        return;
                    }
                    parentCategory.getChildren().add(categoryDTO);
                })
                .filter(categoryDTO -> categoryDTO.getParentId() == null)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoryDTO getCategory(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(CATEGORY_NOT_FOUND_MESSAGE, id)));

        return categoryAdapter.toDtoWithChildren(category);
    }

    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) throws ImplicactionException {
        Category createdCategory = categoryAdapter.toModel(categoryDTO);
        if (categoryDTO.getParentId() != null) {
            Category parentCategory = categoryRepository.findById(categoryDTO.getParentId())
                    .orElseThrow(() -> new NotFoundException(String.format(CATEGORY_NOT_FOUND_MESSAGE, categoryDTO.getParentId())));
            createdCategory.setParent(parentCategory);
        }

        Category saved = categoryRepository.save(createdCategory);
        return categoryAdapter.toDto(saved);
    }
}
