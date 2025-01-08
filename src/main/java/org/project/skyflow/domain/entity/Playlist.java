package org.project.skyflow.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;

    @ManyToOne
    private User creator;

    @ManyToMany
    @JoinTable(
            name = "playlist_content",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "content_id")
    )
    private Set<Content> contentSet;
}
