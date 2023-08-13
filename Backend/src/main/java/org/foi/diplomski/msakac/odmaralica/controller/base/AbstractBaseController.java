package org.foi.diplomski.msakac.odmaralica.controller.base;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class AbstractBaseController<T, PostDTO, PutDTO, ServiceType extends AbstractBaseService<T, ?, ?, PostDTO, PutDTO>> implements IBaseController<T, PostDTO, PutDTO> {

    protected final ServiceType service;

    public AbstractBaseController(ServiceType service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody PostDTO dto) {
        try {
            T createdEntity = service.create(dto);
            return ResponseEntity.ok(new CreateResponseDTO<T>(createdEntity, HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new CreateResponseDTO<T>(HttpStatus.CONFLICT, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        T entity = service.findById(id);

        if (entity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getNotFoundResponse());
        }

        return ResponseEntity.ok(new CreateResponseDTO<T>(entity, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<T> entities = service.findAll();
        return ResponseEntity.ok(new CreateResponseDTO<List<T>>(entities, HttpStatus.OK));
    }

    @PutMapping()
    public ResponseEntity<Object> update(@Valid @RequestBody PutDTO dto) {
        try {
            T updatedEntity = service.update(dto);
            return ResponseEntity.ok(new CreateResponseDTO<T>(updatedEntity, HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new CreateResponseDTO<T>(HttpStatus.CONFLICT, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        T existingEntity = service.findById(id);

        if (existingEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getNotFoundResponse());
        }

        try {
            service.delete(id);
            return ResponseEntity.ok(new CreateResponseDTO<T>(HttpStatus.OK, "Entity deleted"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new CreateResponseDTO<T>(HttpStatus.CONFLICT, e.getMessage()));
        }
    }

    @GetMapping("/find")
    public ResponseEntity<Object> queryCountries(@RequestParam("q") String queryParams) {
        // FIXME: Osim q parametara trebam jos sort=, offset, limit
        List<T> entities = service.find(queryParams);

        if (entities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getNotFoundResponse());
        }

        return ResponseEntity.ok(new CreateResponseDTO<List<T>>(entities, HttpStatus.OK));
    }

    public CreateResponseDTO<T> getNotFoundResponse() {
        return new CreateResponseDTO<T>(HttpStatus.NOT_FOUND, "Entity not found");
    }
}
