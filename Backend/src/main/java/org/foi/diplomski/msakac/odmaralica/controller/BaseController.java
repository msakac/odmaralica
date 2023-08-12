package org.foi.diplomski.msakac.odmaralica.controller;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

public abstract class BaseController<T, PostDTO, PutDTO,
                                ServiceType extends BaseService<T,?,?,PostDTO,PutDTO>> {

    protected final ServiceType service;
    public BaseController(ServiceType service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody PostDTO dto) {
        T createdEntity = service.create(dto);
        return ResponseEntity.ok(new CreateResponseDTO<>(createdEntity, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        T entity = service.findById(id);

        if (entity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getNotFoundResponse());
        }

        return ResponseEntity.ok(new CreateResponseDTO<>(entity, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<T> entities = service.findAll();
        return ResponseEntity.ok(new CreateResponseDTO<>(entities, HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody PutDTO dto) {
        T existingEntity = service.findById(id);

        if (existingEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getNotFoundResponse());
        }

        T updatedEntity = service.update(dto);
        return ResponseEntity.ok(new CreateResponseDTO<>(updatedEntity, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        T existingEntity = service.findById(id);

        if (existingEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getNotFoundResponse());
        }

        service.delete(id);
        return ResponseEntity.ok(new CreateResponseDTO<>(HttpStatus.OK, "Entity deleted"));
    }

    protected abstract CreateResponseDTO<T> getNotFoundResponse();
}
