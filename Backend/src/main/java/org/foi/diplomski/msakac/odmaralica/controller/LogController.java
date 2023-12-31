package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.controller.base.AbstractBaseController;
import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.EncryptedLogGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.get.LogGetDTO;
import org.foi.diplomski.msakac.odmaralica.dto.post.LogPostDTO;
import org.foi.diplomski.msakac.odmaralica.dto.put.LogPutDTO;
import org.foi.diplomski.msakac.odmaralica.model.Log;
import org.foi.diplomski.msakac.odmaralica.service.implementation.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController extends AbstractBaseController<Log, LogGetDTO, LogPostDTO, LogPutDTO, LogServiceImpl> {
    @Autowired
    public LogController(LogServiceImpl service) {
        super(service);
    }

    @GetMapping("/encrypted")
    public ResponseEntity<Object> getAll() {
        List<EncryptedLogGetDTO> entities = service.findAllEncrypt();
        return ResponseEntity.ok(new CreateResponseDTO<List<EncryptedLogGetDTO>>(entities, HttpStatus.OK));
    }

    @Override
    public CreateResponseDTO<Log> getNotFoundResponse() {
        return new CreateResponseDTO<Log>(HttpStatus.NOT_FOUND, "Log not found");
    }

}
