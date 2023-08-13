package org.foi.diplomski.msakac.odmaralica.service;

import org.apache.commons.lang3.NotImplementedException;
import org.foi.diplomski.msakac.odmaralica.utils.QueryBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

public abstract class AbstractBaseService<T, RepositoryType extends JpaRepository<T, Long>, Mapper, PostDTO, PutDTO>
        implements IBaseService<T, RepositoryType, Mapper, PostDTO, PutDTO> {

    protected final RepositoryType repository;
    protected final Mapper mapper;
    private final EntityManager entityManager;

    public AbstractBaseService(RepositoryType repository, Mapper mapper, EntityManager entityManager) {
        this.repository = repository;
        this.mapper = mapper;
        this.entityManager = entityManager;
    }

    @Override
    public T convertPost(PostDTO entityPost) {
        throw new NotImplementedException();
    }

    @Override
    public T convertPut(PutDTO entityPut) {
        throw new NotImplementedException();
    }

    @Override
    public T create(PostDTO entityPost) {
        final T entity = this.convertPost(entityPost);
        return repository.save(entity);
    }

    @Override
    public T findById(Long id) {
        Optional<T> optionalEntity = repository.findById(id);
        return optionalEntity.orElse(null);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T update(PutDTO entityPut) {
        final T entity = this.convertPut(entityPut);
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<T> find(String queryParams) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        QueryBuilder<T> queryBuilder = new QueryBuilder<>(criteriaBuilder, getEntityClass());
        CriteriaQuery<T> query = queryBuilder.buildQuery(queryParams);
        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public Class<T> getEntityClass() {
        throw new NotImplementedException();
    }
}
