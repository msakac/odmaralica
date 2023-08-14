package org.foi.diplomski.msakac.odmaralica.service.base;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBaseService<T, RepositoryType extends JpaRepository<T, Long>, Mapper, GetDTO, PostDTO, PutDTO> {

    T convertPost(PostDTO entityPost);

    T convertPut(PutDTO entityPut);

    GetDTO convertGet(T entityPut);

    GetDTO create(PostDTO entityPost);

    GetDTO findById(Long id);

    List<GetDTO> findAll();

    GetDTO update(PutDTO entityPut);

    void delete(Long id);

    List<GetDTO> find(String queryParams);

    Class<T> getEntityClass();
}
