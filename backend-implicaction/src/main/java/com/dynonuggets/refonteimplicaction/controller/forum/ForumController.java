package com.dynonuggets.refonteimplicaction.controller.forum;

import com.dynonuggets.refonteimplicaction.dto.forum.*;
import com.dynonuggets.refonteimplicaction.exception.ImplicactionException;
import com.dynonuggets.refonteimplicaction.service.forum.CategoryService;
import com.dynonuggets.refonteimplicaction.service.forum.ResponseService;
import com.dynonuggets.refonteimplicaction.service.forum.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/forum")
@AllArgsConstructor
public class ForumController {
    private final CategoryService categoryService;
    private final TopicService topicService;
    private final ResponseService responseService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() throws ImplicactionException {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CreateCategoryDto categoryDto) {
        CategoryDto saveDto = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(CREATED).body(saveDto);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable long categoryId) {
        CategoryDto foundDto = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(foundDto);
    }

    @GetMapping("/categories/{categoryId}/topics") // NEED TO VALIDATE
    public ResponseEntity<Page<TopicDto>> getTopicsFromCategory(
            @PathVariable long categoryId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "rows", defaultValue = "10") int rows,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "ASC") String sortOrder
    ) {
        Pageable pageable = PageRequest.of(page, rows, Sort.by(Sort.Direction.valueOf(sortOrder), sortBy));
        Page<TopicDto> topicDtos = topicService.getTopicsFromCategory(categoryId, pageable);
        return ResponseEntity.ok(topicDtos);
    }

    @PostMapping("/topics")
    public ResponseEntity<TopicDto> createTopic(@RequestBody CreateTopicDto topicDto) {
        TopicDto saveDto = topicService.createTopic(topicDto);
        return ResponseEntity.status(CREATED).body(saveDto);
    }

    @GetMapping("/topics/{topicId}")
    public ResponseEntity<TopicDto> getTopic(@PathVariable long topicId) {
        TopicDto foundDto = topicService.getTopic(topicId);
        return ResponseEntity.ok(foundDto);
    }

    @GetMapping("/responses/{topicId}") // NEED TO VALIDATE
    public ResponseEntity<Page<ResponseDto>> getResponsesFromCategory(
            @PathVariable long topicId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "rows", defaultValue = "10") int rows,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "ASC") String sortOrder
    ) {
        Pageable pageable = PageRequest.of(page, rows, Sort.by(Sort.Direction.valueOf(sortOrder), sortBy));
        Page<ResponseDto> responseDtos = responseService.getResponsesFromTopic(topicId, pageable);
        return ResponseEntity.ok(responseDtos);
    }

    @PostMapping("/responses")
    public ResponseEntity<ResponseDto> createResponse(@RequestBody ResponseDto responseDto) {
        ResponseDto saveDto = responseService.createResponse(responseDto);
        return ResponseEntity.status(CREATED).body(saveDto);
    }

}
