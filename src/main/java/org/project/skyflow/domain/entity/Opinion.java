package org.project.skyflow.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String text;
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.EAGER)
    private Account creator;

    @ManyToOne(fetch = FetchType.LAZY)
    private Content content;

}
