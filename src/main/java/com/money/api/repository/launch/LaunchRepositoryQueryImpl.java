package com.money.api.repository.launch;

import com.money.api.model.Launch;
import com.money.api.model.Launch_;
import com.money.api.repository.filter.LaunchFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LaunchRepositoryQueryImpl implements LaunchRepositoryQuery {

  @PersistenceContext
  private EntityManager manager;

  @Override
  public Page<Launch> filter(LaunchFilter filter, Pageable page) {

    var builder = manager.getCriteriaBuilder();
    CriteriaQuery<Launch> criteria = builder.createQuery(Launch.class);
    Root<Launch> root = criteria.from(Launch.class);


    Predicate[] predicates = createRestrictions(filter, builder, root);
    criteria.where(predicates);

    TypedQuery<Launch> query = manager.createQuery(criteria);
    addRestrictionsOfPagination(query, page);

    return new PageImpl<>(query.getResultList(), page, total(filter));
  }

  private Predicate[] createRestrictions(LaunchFilter filter, CriteriaBuilder builder, Root<Launch> root) {
    List<Predicate> predicates = new ArrayList<>();

    if (!StringUtils.isEmpty(filter.getDescription())) {
      predicates.add(builder.like(builder.lower(root.get(Launch_.description)),
        "%" + filter.getDescription().toLowerCase() + "%"));
    }

    if (filter.getDueDateIn() != null) {
      predicates.add(builder.greaterThanOrEqualTo(root.get(Launch_.dueDate),
        filter.getDueDateIn()));
    }

    if (filter.getDueDateUntil() != null) {
      predicates.add(builder.lessThanOrEqualTo(root.get(Launch_.dueDate),
        filter.getDueDateUntil()));
    }

    return predicates.toArray(new Predicate[predicates.size()]);
  }

  private void addRestrictionsOfPagination(TypedQuery<Launch> query, Pageable page) {
    int currentPage = page.getPageNumber();
    int totalRecordsPerPage = page.getPageSize();
    int firstPageRecord = currentPage * totalRecordsPerPage;

    query.setFirstResult(firstPageRecord);
    query.setMaxResults(totalRecordsPerPage);
  }

  private Long total(LaunchFilter filter) {
    var builder = manager.getCriteriaBuilder();
    CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
    Root<Launch> root = criteria.from(Launch.class);

    Predicate[] predicates = createRestrictions(filter, builder, root);
    criteria.where(predicates);

    criteria.select(builder.count(root));
    return manager.createQuery(criteria).getSingleResult();
  }
}
