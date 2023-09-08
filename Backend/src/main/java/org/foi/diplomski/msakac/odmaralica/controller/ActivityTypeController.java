package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.controller.base.AbstractBaseController;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.ActivityTypeGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ActivityTypePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ActivityTypePutDTO;
import org.foi.diplomski.msakac.odmaralica.model.ActivityType;
import org.foi.diplomski.msakac.odmaralica.service.implementation.ActivityTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity-type")
public class ActivityTypeController extends AbstractBaseController<ActivityType, ActivityTypeGetDTO, ActivityTypePostDTO, ActivityTypePutDTO, ActivityTypeServiceImpl> {

    @Autowired
    public ActivityTypeController(ActivityTypeServiceImpl service) {
        super(service);
    }

    @Override
    public CreateResponseDTO<ActivityType> getNotFoundResponse() {
        return new CreateResponseDTO<ActivityType>(HttpStatus.NOT_FOUND, "Activity type not found");
    }
}
