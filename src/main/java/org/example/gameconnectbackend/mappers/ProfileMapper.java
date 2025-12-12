package org.example.gameconnectbackend.mappers;

import org.example.gameconnectbackend.dtos.postDtos.ProfileDTO;
import org.example.gameconnectbackend.models.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(target = "followers", expression = "java(profile.getFollowers() != null ? profile.getFollowers().size() : 0)")
    @Mapping(target = "followings", expression = "java(profile.getFollowing() != null ? profile.getFollowing().size() : 0)")
    ProfileDTO toDto(Profile profile);
}
