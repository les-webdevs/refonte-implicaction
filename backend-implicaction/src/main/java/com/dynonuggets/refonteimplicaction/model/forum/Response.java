package com.dynonuggets.refonteimplicaction.model.forum;

import com.dynonuggets.refonteimplicaction.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "response")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message", nullable = false)
    @NotBlank(message = "message is required")
    private String message;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "edited_at", nullable = false)
    private Instant editedAt;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

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
