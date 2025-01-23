package org.project.skyflow.service;

public interface FollowService {
    void followAccount(long accountId);
    void unfollowAccount(long accountId);
}
