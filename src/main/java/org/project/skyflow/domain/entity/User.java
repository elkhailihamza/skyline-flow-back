package org.project.skyflow.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Suspension> suspensions;
    private boolean isSuspended;
    private boolean isActive;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @JsonManagedReference
    @OneToOne
    private Account account;

    @JsonManagedReference
    @OneToMany(mappedBy = "follower", fetch = FetchType.EAGER)
    private List<Follow> followings;

    @JsonManagedReference
    @OneToMany(mappedBy = "voter", fetch = FetchType.EAGER)
    private List<Vote> votes;
}
