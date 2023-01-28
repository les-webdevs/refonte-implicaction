package com.dynonuggets.refonteimplicaction.repository.forum;

import com.dynonuggets.refonteimplicaction.model.User;
import com.dynonuggets.refonteimplicaction.model.forum.Category;
import com.dynonuggets.refonteimplicaction.model.forum.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    // TODO: use criteria to order depending on a parameter
    Page<Topic> findByCategoryOrderByEditedAt(Category category, Pageable pageable);

    List<Topic> findByCategoryOrderByEditedAt(Category category);

    Page<Topic> findByAuthorOrderByEditedAt(User author, Pageable pageable);
}
