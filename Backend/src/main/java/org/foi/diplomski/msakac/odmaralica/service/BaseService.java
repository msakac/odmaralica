package org.foi.diplomski.msakac.odmaralica.service;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;



public abstract class BaseService<T, RepositoryType extends JpaRepository<T, Long>,
                                Mapper, PostDTO, PutDTO> {


    protected final RepositoryType repository;
    protected final Mapper mapper;
    public BaseService(RepositoryType repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public T convertPost(PostDTO entityPost) {
        throw new NotImplementedException();
    }

    public T convertPut(PutDTO entityPut) {
        throw new NotImplementedException();
    }

    public T create(PostDTO entityPost) {
        final T entity = this.convertPost(entityPost);
        return repository.save(entity);
    }

    public T findById(Long id) {
        Optional<T> optionalEntity = repository.findById(id);
        return optionalEntity.orElse(null);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T update(PutDTO entityPut) {
        final T entity = this.convertPut(entityPut);
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
