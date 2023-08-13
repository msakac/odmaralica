package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.model.Log;
import org.foi.diplomski.msakac.odmaralica.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping
    public ResponseEntity<Log> createLog(@RequestBody Log log) {
        Log createdLog = logService.createLog(log);
        return ResponseEntity.ok(createdLog);
    }
}
