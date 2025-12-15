package org.example.gameconnectbackend.interfaces;

import org.example.gameconnectbackend.dtos.postDtos.TimelinePostDTO;

import java.util.List;

public interface IExploreService {
    List<TimelinePostDTO> getExploreFeed(long currentUserId);
}
