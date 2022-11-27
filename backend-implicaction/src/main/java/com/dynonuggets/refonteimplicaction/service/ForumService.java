package com.dynonuggets.refonteimplicaction.service;

import com.dynonuggets.refonteimplicaction.repository.forum.CategoryRepository;
import com.dynonuggets.refonteimplicaction.repository.forum.ResponseRepository;
import com.dynonuggets.refonteimplicaction.repository.forum.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ForumService {
    private final CategoryRepository categoryRepository;
    private final TopicRepository topicRepository;
    private final ResponseRepository responseRepository;
    

}
