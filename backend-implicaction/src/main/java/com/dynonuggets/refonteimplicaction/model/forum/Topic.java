package com.dynonuggets.refonteimplicaction.model.forum;

import com.dynonuggets.refonteimplicaction.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    @NotBlank(message = "title is required")
    private String title;

    @Column(name = "message", nullable = false)
    @NotBlank(message = "message is required")
    private String message;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "edited_at", nullable = false)
    private Instant editedAt;

    @Column(name = "is_locked", nullable = false)
    private boolean isLocked = false;

    @Column(name = "is_pinned", nullable = false)
    private boolean isPinned = false;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @OneToMany(mappedBy = "topic")
    private List<Response> responses;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @PrePersist
    void createdAt() {
        Instant now = Instant.now();
        createdAt = now;
        setUpdatedAt(now);
    }

    @PreUpdate
    void updatedAt() {
        setUpdatedAt(Instant.now());
    }

    private void setUpdatedAt(Instant now) {
        editedAt = now;
    }
}
