package org.foi.diplomski.msakac.odmaralica.service;

import org.foi.diplomski.msakac.odmaralica.dto.get.ActivityTypeGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ActivityTypePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ActivityTypePutDTO;
import org.foi.diplomski.msakac.odmaralica.model.ActivityType;

import java.util.List;

public interface IActivityTypeService {
    ActivityType convertPost(ActivityTypePostDTO entityPost);

    ActivityType convertPut(ActivityTypePutDTO entityPut);

    ActivityTypeGetDTO convertGet(ActivityType entity);

    ActivityTypeGetDTO create(ActivityTypePostDTO entityPost);

    ActivityTypeGetDTO findById(Long id);

    ActivityType findByName(String name);

    List<ActivityTypeGetDTO> findAll();

    ActivityTypeGetDTO update(ActivityTypePutDTO entityPut);

    void delete(Long id);

    List<ActivityTypeGetDTO> find(String queryParams);

    Class<ActivityType> getEntityClass();
}
