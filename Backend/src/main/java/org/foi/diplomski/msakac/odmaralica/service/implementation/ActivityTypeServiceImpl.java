package org.foi.diplomski.msakac.odmaralica.service.implementation;

import javax.persistence.EntityManager;

import org.foi.diplomski.msakac.odmaralica.dto.get.ActivityTypeGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.ActivityTypePostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.ActivityTypePutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.ActivityTypeMapper;
import org.foi.diplomski.msakac.odmaralica.model.ActivityType;
import org.foi.diplomski.msakac.odmaralica.repository.ActivityTypeRepository;
import org.foi.diplomski.msakac.odmaralica.service.IActivityTypeService;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityTypeServiceImpl extends AbstractBaseService<ActivityType, ActivityTypeRepository, ActivityTypeMapper, ActivityTypeGetDTO, ActivityTypePostDTO, ActivityTypePutDTO> implements IActivityTypeService {
    @Autowired
    public ActivityTypeServiceImpl(ActivityTypeRepository repository, ActivityTypeMapper mapper, EntityManager entityManager) {
        super(repository, mapper, entityManager);
    }

    @Override
    public ActivityTypeGetDTO create(ActivityTypePostDTO entityPost) {
        //check if entity with same name exists
        if (repository.findByName(entityPost.getName()) != null) {
            //throw exception
            throw new RuntimeException("Activity type with same name already exists");
        }
        return super.create(entityPost);
    }

    @Override
    public ActivityType convertPost(ActivityTypePostDTO entityPost) {
        return mapper.convert(entityPost);
    }

    @Override
    public ActivityType convertPut(ActivityTypePutDTO entityPut) {
        return mapper.convert(entityPut);
    }

    @Override
    public ActivityTypeGetDTO convertGet(ActivityType entity) {
        return mapper.convert(entity);
    }

    public Class<ActivityType> getEntityClass() {
        return ActivityType.class;
    }

    @Override
    public ActivityType findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public ActivityTypeGetDTO update(ActivityTypePutDTO entityPut) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
