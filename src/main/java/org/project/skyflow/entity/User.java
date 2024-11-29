package org.project.skyflow.entity;

import jakarta.persistence.*;
import lombok.*;
import org.project.skyflow.entity.type.Role;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "\"user\"")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    private boolean isSuspended;
    private String suspensionReason;
    private LocalDateTime suspensionEndTime;
    private boolean isActive;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    private Account account;

    @OneToMany(mappedBy = "follower")
    private List<Follow> followings;

    @OneToMany(mappedBy = "voter")
    private List<Vote> votes;
}
