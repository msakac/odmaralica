package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.dto.get.LogGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.LogPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.LogPutDTO;
import org.foi.diplomski.msakac.odmaralica.mapper.LogMapper;
import org.foi.diplomski.msakac.odmaralica.model.Log;
import org.foi.diplomski.msakac.odmaralica.repository.LogRepository;
import org.foi.diplomski.msakac.odmaralica.service.ILogService;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class LogServiceImpl extends AbstractBaseService<Log, LogRepository, LogMapper, LogGetDTO, LogPostDTO, LogPutDTO> implements ILogService {


    @Autowired
    public LogServiceImpl(LogRepository repository, LogMapper mapper, EntityManager entityManager) {
        super(repository, mapper, entityManager);
    }

    @Override
    public Log convertPost(LogPostDTO entityPost) {
        return mapper.convert(entityPost);
    }

    @Override
    public Log convertPut(LogPutDTO entityPut) {
        return mapper.convert(entityPut);
    }

    @Override
    public LogGetDTO convertGet(Log entity) {
        return mapper.convert(entity);
    }

    public Class<Log> getEntityClass() {
        return Log.class;
    }
}
