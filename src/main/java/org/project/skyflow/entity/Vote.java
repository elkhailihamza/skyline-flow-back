package org.project.skyflow.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User voter;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;
    private boolean isUpvoted;
}
