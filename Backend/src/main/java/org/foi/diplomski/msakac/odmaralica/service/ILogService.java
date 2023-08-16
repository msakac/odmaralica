package org.foi.diplomski.msakac.odmaralica.service;

import org.foi.diplomski.msakac.odmaralica.dto.get.LogGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.LogPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.LogPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Log;

import java.util.List;

public interface ILogService {
    Log convertPost(LogPostDTO entityPost);

    Log convertPut(LogPutDTO entityPut);

    LogGetDTO convertGet(Log entity);

    LogGetDTO create(LogPostDTO entityPost);

    LogGetDTO findById(Long id);

    List<LogGetDTO> findAll();

    LogGetDTO update(LogPutDTO entityPut);

    void delete(Long id);

    List<LogGetDTO> find(String queryParams);

    Class<Log> getEntityClass();
}
