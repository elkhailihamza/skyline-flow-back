package org.project.skyflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "\"name\"", nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true)
    private String email;

    @Column(name = "\"password\"", nullable = false)
    private String password;

    @OneToMany
    private List<Suspension> suspension;
    private boolean isSuspended;
    private boolean isActive;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToOne(fetch = FetchType.LAZY)
    private Account account;

    @OneToMany(mappedBy = "follower")
    private List<Follow> followings;

    @OneToMany(mappedBy = "voter")
    private List<Vote> votes;
}
