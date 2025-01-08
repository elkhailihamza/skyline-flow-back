package org.project.skyflow.dto;

import org.project.skyflow.domain.entity.*;

import java.util.List;

public class UserDTO {
    private long id;
    private String name;
    private String surname;
    private String email;
    private List<Suspension> suspensions;
    private boolean isSuspended;
    private boolean isActive;
    private List<Role> roles;
    private long account;
    private List<Follow> followings;
    private List<Vote> votes;
}
