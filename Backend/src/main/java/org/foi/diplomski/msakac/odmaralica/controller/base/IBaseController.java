package org.foi.diplomski.msakac.odmaralica.controller.base;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public interface IBaseController<T, PostDTO, PutDTO> {

    ResponseEntity<Object> create(@Valid PostDTO dto);

    ResponseEntity<Object> getById(Long id);

    ResponseEntity<Object> getAll();

    ResponseEntity<Object> update(@Valid PutDTO dto);

    ResponseEntity<Object> delete(Long id);

    ResponseEntity<Object> queryCountries(String queryParams);

    CreateResponseDTO<T> getNotFoundResponse();
}
