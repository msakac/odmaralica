package org.foi.diplomski.msakac.odmaralica.mapper;

import org.foi.diplomski.msakac.odmaralica.dto.get.ActivityTypeGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.LogGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.LogPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.ActivityType;
import org.foi.diplomski.msakac.odmaralica.model.Log;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.dto.post.LogPostDTO;
import org.foi.diplomski.msakac.odmaralica.service.IActivityTypeService;
import org.foi.diplomski.msakac.odmaralica.service.security.implementation.UserServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class LogMapper {
    @Autowired
    protected UserServiceImpl userService;
    @Autowired
    protected IActivityTypeService activityTypeService;

    protected ActivityTypeMapper activityTypeMapper = Mappers.getMapper(ActivityTypeMapper.class);

    @Mapping(source = "logPostDTO.userId", target = "user")
    @Mapping(source = "logPostDTO.activityTypeId", target = "activityType")
    public abstract Log convert(LogPostDTO logPostDTO);

    @Mapping(source = "logPutDTO.userId", target = "user")
    @Mapping(source = "logPutDTO.activityTypeId", target = "activityType")
    public abstract Log convert(LogPutDTO logPutDTO);

    public abstract LogGetDTO convert(Log log);

    protected User mapToUser(Long userId) {
        if (userId == null) {
            return null;
        }
        return userService.findById(userId);
    }

    protected ActivityType mapToActivityType(Long activityTypeId) {
        ActivityTypeGetDTO activityTypeGetDTO = activityTypeService.findById(activityTypeId);
        return activityTypeMapper.convert(activityTypeGetDTO);
    }

}
