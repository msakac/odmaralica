package org.foi.diplomski.msakac.odmaralica.service.base;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBaseService<T, RepositoryType extends JpaRepository<T, Long>, Mapper, PostDTO, PutDTO> {

    T convertPost(PostDTO entityPost);

    T convertPut(PutDTO entityPut);

    T create(PostDTO entityPost);

    T findById(Long id);

    List<T> findAll();

    T update(PutDTO entityPut);

    void delete(Long id);

    List<T> find(String queryParams);

    Class<T> getEntityClass();
}
