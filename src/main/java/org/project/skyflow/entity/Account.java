package org.project.skyflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    @OneToOne(mappedBy = "account", fetch = FetchType.EAGER)
    private User user;
    private String bio;
    private String profilePicture;
    private LocalDate createdAt;

    @OneToMany(mappedBy = "follower")
    private List<Follow> followers;

    @OneToMany(mappedBy = "creator")
    private List<Content> contentList;
}
