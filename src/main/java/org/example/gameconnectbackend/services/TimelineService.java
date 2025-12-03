package org.example.gameconnectbackend.services;

import org.example.gameconnectbackend.interfaces.ITimelineService;
import org.example.gameconnectbackend.mappers.TimelineMapper;
import org.example.gameconnectbackend.repositories.TimelineRepository;
import org.springframework.stereotype.Service;

@Service
public class TimelineService implements ITimelineService {

    private final TimelineRepository timelineRepository;
    private final TimelineMapper timelineMapper;


    public TimelineService(TimelineRepository timelineRepository, TimelineMapper timelineMapper) {
        this.timelineRepository = timelineRepository;
        this.timelineMapper = timelineMapper;
    }



}
