package org.foi.diplomski.msakac.odmaralica.controller.base;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.foi.diplomski.msakac.odmaralica.exceptions.InvalidRequestResponseBuilder;
import org.foi.diplomski.msakac.odmaralica.service.base.AbstractBaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//TODO: Conflict exception da ima lepsi ispis
public abstract class AbstractBaseController<T, GetDTO, PostDTO, PutDTO, ServiceType
        extends AbstractBaseService<T, ?, ?, GetDTO, PostDTO, PutDTO>>
        implements IBaseController<T, GetDTO, PostDTO, PutDTO> {

  protected final ServiceType service;

  public AbstractBaseController(ServiceType service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Object> create(@Valid @RequestBody PostDTO dto) {
    try {
      GetDTO createdEntity = service.create(dto);
      return ResponseEntity.ok(new CreateResponseDTO<GetDTO>(createdEntity, HttpStatus.OK));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(InvalidRequestResponseBuilder.createResponse(e));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getById(@PathVariable Long id) {
    GetDTO entity = service.findById(id);

    if (entity == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getNotFoundResponse());
    }
    return ResponseEntity.ok(new CreateResponseDTO<GetDTO>(entity, HttpStatus.OK));
  }

  @GetMapping
  public ResponseEntity<Object> getAll() {
    List<GetDTO> entities = service.findAll();
    return ResponseEntity.ok(new CreateResponseDTO<List<GetDTO>>(entities, HttpStatus.OK));
  }

  @PutMapping()
  public ResponseEntity<Object> update(@Valid @RequestBody PutDTO dto) {
    try {
      GetDTO updatedEntity = service.update(dto);
      return ResponseEntity.ok(new CreateResponseDTO<GetDTO>(updatedEntity, HttpStatus.OK));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(InvalidRequestResponseBuilder.createResponse(e));
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    GetDTO existingEntity = service.findById(id);

    if (existingEntity == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getNotFoundResponse());
    }

    try {
      service.delete(id);
      return ResponseEntity.ok(new CreateResponseDTO<T>(HttpStatus.OK, "Entity deleted"));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(InvalidRequestResponseBuilder.createResponse(e));
    }
  }

  @GetMapping("/find")
  public ResponseEntity<Object> queryCountries(@RequestParam("q") String queryParams) {
    try {
      List<GetDTO> entities = service.find(queryParams);
      return ResponseEntity.ok(new CreateResponseDTO<List<GetDTO>>(entities, HttpStatus.OK));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(InvalidRequestResponseBuilder.createResponse(e));
    }
  }

  public CreateResponseDTO<T> getNotFoundResponse() {
    return new CreateResponseDTO<T>(HttpStatus.NOT_FOUND, "Entity not found");
  }
}
