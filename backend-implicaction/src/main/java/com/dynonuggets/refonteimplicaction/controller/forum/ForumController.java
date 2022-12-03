package com.dynonuggets.refonteimplicaction.controller.forum;

import com.dynonuggets.refonteimplicaction.dto.forum.CategoryDTO;
import com.dynonuggets.refonteimplicaction.exception.ImplicactionException;
import com.dynonuggets.refonteimplicaction.service.forum.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/forum")
@AllArgsConstructor
public class ForumController {
    private final CategoryService forumService;

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
}
