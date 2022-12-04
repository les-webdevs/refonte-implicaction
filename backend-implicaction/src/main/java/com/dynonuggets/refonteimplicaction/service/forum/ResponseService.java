package com.dynonuggets.refonteimplicaction.service.forum;

import com.dynonuggets.refonteimplicaction.adapter.forum.ResponseAdapter;
import com.dynonuggets.refonteimplicaction.dto.forum.ResponseDto;
import com.dynonuggets.refonteimplicaction.model.forum.Response;
import com.dynonuggets.refonteimplicaction.model.forum.Topic;
import com.dynonuggets.refonteimplicaction.repository.forum.ResponseRepository;
import com.dynonuggets.refonteimplicaction.repository.forum.TopicRepository;
import com.dynonuggets.refonteimplicaction.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ResponseService {
    ResponseRepository responseRepository;
    TopicRepository topicRepository;
    ResponseAdapter responseAdapter;
    AuthService authService;

    public Page<ResponseDto> getResponsesFromTopic(long topicId, Pageable pageable) {
        Topic topic = topicRepository.getById(topicId);
        return responseRepository.findAllByTopic(topic, pageable).map(responseAdapter::toDto);
    }

    public ResponseDto createResponse(ResponseDto responseDto) {
        Response response = responseAdapter.toModel(responseDto, authService.getCurrentUser());
        Response save = responseRepository.save(response);
        return responseAdapter.toDto(save);
    }

}
