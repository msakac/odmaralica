package org.foi.diplomski.msakac.odmaralica.service.base;

import org.apache.commons.lang3.NotImplementedException;
import org.foi.diplomski.msakac.odmaralica.utils.QueryBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractBaseService<T, RepositoryType extends JpaRepository<T, Long>, Mapper, GetDTO, PostDTO, PutDTO>
        implements IBaseService<T, RepositoryType, Mapper, GetDTO, PostDTO, PutDTO> {

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
  public GetDTO convertGet(T entity) {
    throw new NotImplementedException();
  }

  @Override
  public GetDTO create(PostDTO entityPost) {
    final T entity = this.convertPost(entityPost);
    T savedEntity = repository.save(entity);
    return this.convertGet(savedEntity);
  }

  @Override
  public GetDTO findById(Long id) {
    Optional<T> optionalEntity = repository.findById(id);
    T entity = optionalEntity.orElse(null);
    GetDTO getEntity = this.convertGet(entity);
    return getEntity;
  }

  @Override
  public List<GetDTO> findAll() {
    List<T> entities = repository.findAll();
    List<GetDTO> getEntities = new ArrayList<>();
    for (T entity : entities) {
      getEntities.add(this.convertGet(entity));
    }
    return getEntities;
  }

  @Override
  public GetDTO update(PutDTO entityPut) {
    final T entity = this.convertPut(entityPut);
    T savedEntity = repository.save(entity);
    return this.convertGet(savedEntity);
  }

  @Override
  public void delete(Long id) {
    repository.deleteById(id);
  }

  @Override
  public List<GetDTO> find(String queryParams) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    QueryBuilder<T> queryBuilder = new QueryBuilder<>(criteriaBuilder, getEntityClass());
    CriteriaQuery<T> query = queryBuilder.buildQuery(queryParams);
    TypedQuery<T> typedQuery = entityManager.createQuery(query);

    List<GetDTO> getEntities = new ArrayList<>();
    for (T entity : typedQuery.getResultList()) {
      getEntities.add(this.convertGet(entity));
    }
    return getEntities;
  }

  @Override
  public Class<T> getEntityClass() {
    throw new NotImplementedException();
  }
}
