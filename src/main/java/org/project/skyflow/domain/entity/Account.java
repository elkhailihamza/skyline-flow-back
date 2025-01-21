package org.project.skyflow.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonBackReference
    @OneToOne(mappedBy = "account")
    private User user;

    private String bio;
    private String profilePicture;
    private LocalDate createdAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "following")
    private List<Follow> followers;

    @JsonManagedReference
    @OneToMany(mappedBy = "creator")
    private List<Content> contentList;
}
