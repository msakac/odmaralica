package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.model.ActivityType;
import org.foi.diplomski.msakac.odmaralica.service.IActivityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity-types")
public class ActivityTypeController {

    private final IActivityTypeService activityTypeService;

    @Autowired
    public ActivityTypeController(IActivityTypeService activityTypeService) {
        this.activityTypeService = activityTypeService;
    }

    @PostMapping
    public ResponseEntity<ActivityType> createActivityType(@RequestBody ActivityType activityType) {
        ActivityType createdActivityType = activityTypeService.createActivityType(activityType);
        return ResponseEntity.ok(createdActivityType);
    }
}
