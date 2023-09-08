package org.foi.diplomski.msakac.odmaralica.mapper;

import org.foi.diplomski.msakac.odmaralica.dto.get.ActivityTypeGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ActivityTypePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ActivityTypePutDTO;
import org.foi.diplomski.msakac.odmaralica.model.ActivityType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActivityTypeMapper {
    ActivityTypeMapper INSTANCE = Mappers.getMapper(ActivityTypeMapper.class);

    ActivityType convert(ActivityTypePutDTO activityTypePutDTO);

    ActivityType convert(ActivityTypePostDTO activityTypePostDTO);

    ActivityTypeGetDTO convert(ActivityType activityType);

    ActivityType convert(ActivityTypeGetDTO activityTypeGetDTO);
}
