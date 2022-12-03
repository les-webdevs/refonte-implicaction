package com.dynonuggets.refonteimplicaction.service.forum;

import com.dynonuggets.refonteimplicaction.adapter.forum.TopicAdapter;
import com.dynonuggets.refonteimplicaction.dto.forum.TopicDTO;
import com.dynonuggets.refonteimplicaction.exception.NotFoundException;
import com.dynonuggets.refonteimplicaction.model.forum.Category;
import com.dynonuggets.refonteimplicaction.model.forum.Topic;
import com.dynonuggets.refonteimplicaction.repository.forum.CategoryRepository;
import com.dynonuggets.refonteimplicaction.repository.forum.TopicRepository;
import com.dynonuggets.refonteimplicaction.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import static com.dynonuggets.refonteimplicaction.utils.Message.CATEGORY_NOT_FOUND_MESSAGE;

@AllArgsConstructor
@Service
public class TopicService {
    private TopicRepository topicRepository;
    private CategoryRepository categoryRepository;
    private TopicAdapter topicAdapter;

    private final AuthService authService;

    public Page<TopicDTO> getTopicsFromCategory(long categoryId, Pageable pageable) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(String.format(CATEGORY_NOT_FOUND_MESSAGE, categoryId)));
        return topicRepository.findByCategoryOrderByEditedAt(category, pageable).map(topicAdapter::toDto);
    }

    public TopicDTO createTopic(TopicDTO topicDTO) {
        Topic topic = topicAdapter.toModel(topicDTO, authService.getCurrentUser());
        Topic save = topicRepository.save(topic);
        return topicAdapter.toDto(save);
    }

}
