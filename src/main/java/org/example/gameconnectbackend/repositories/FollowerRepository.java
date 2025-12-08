package org.example.gameconnectbackend.repositories;

import org.example.gameconnectbackend.models.Follower;
import org.example.gameconnectbackend.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower, Long> {
    boolean existsByFollower_IdAndFollowing_Id(long followerId, long followingId);
    Follower findByFollowerAndFollowing(Profile follow, Profile following);
}
