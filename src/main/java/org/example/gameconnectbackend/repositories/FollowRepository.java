package org.example.gameconnectbackend.repositories;

import org.example.gameconnectbackend.models.Follower;
import org.example.gameconnectbackend.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follower, Long> {
    boolean existsByFollower_IdAndFollowing_Id(long followerId, long followingId);
    Follower findByFollowerAndFollowing(Profile follow, Profile following);
    List<Follower> findAllByFollower(Profile follower);
    List<Follower> findAllByFollowing(Profile following);
}
