package com.dynonuggets.refonteimplicaction.controller.forum;

import com.dynonuggets.refonteimplicaction.dto.forum.CategoryDTO;
import com.dynonuggets.refonteimplicaction.dto.forum.ResponseDTO;
import com.dynonuggets.refonteimplicaction.dto.forum.TopicDTO;
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
    private final CategoryService forumService;
    private final TopicService topicService;
    private final ResponseService responseService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() throws ImplicactionException {
        return ResponseEntity.ok(forumService.getCategories());
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO saveDTO = forumService.createCategory(categoryDTO);
        return ResponseEntity.status(CREATED).body(saveDTO);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable long categoryId) {
        CategoryDTO foundDTO = forumService.getCategory(categoryId);
        return ResponseEntity.ok(foundDTO);
    }

    @GetMapping("/topics/{categoryId}") // NEED TO VALIDATE
    public ResponseEntity<Page<TopicDTO>> getTopicsFromCategory(
            @PathVariable long categoryId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "rows", defaultValue = "10") int rows,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "ASC") String sortOrder
    ) {
        Pageable pageable = PageRequest.of(page, rows, Sort.by(Sort.Direction.valueOf(sortOrder), sortBy));
        Page<TopicDTO> topicDtos = topicService.getTopicsFromCategory(categoryId, pageable);
        return ResponseEntity.ok(topicDtos);
    }

    @GetMapping("/responses/{categortyId}") // NEED TO VALIDATE
    public ResponseEntity<Page<ResponseDTO>> getResponsesFromCategory(
            @PathVariable long categoryId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "rows", defaultValue = "10") int rows,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "ASC") String sortOrder
    ) {
        Pageable pageable = PageRequest.of(page, rows, Sort.by(Sort.Direction.valueOf(sortOrder), sortBy));
        Page<ResponseDTO> responseDtos = responseService.getResponsesFromTopic(categoryId, pageable);
        return ResponseEntity.ok(responseDtos);
    }
}
