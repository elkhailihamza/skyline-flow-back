package org.project.skyflow.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.project.skyflow.domain.entity.type.FollowType;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private FollowType planType;

    @ManyToOne(fetch = FetchType.LAZY)
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account following;

}
