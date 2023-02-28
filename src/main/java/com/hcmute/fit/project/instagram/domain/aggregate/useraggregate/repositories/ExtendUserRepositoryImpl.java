package com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.entities.User;
import com.hcmute.fit.project.instagram.domain.base.ExtendEntityRepositoryBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ExtendUserRepositoryImpl extends ExtendEntityRepositoryBase<User> implements ExtendUserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final String ERROR_INVALID_PARAMETER = "Tham số không hợp lệ";

    @Override
    public List<User> searchUser(Map<String, String> queries) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        return this.dynamicSearchEntity(
                this.entityManager,
                queries,
                criteriaBuilder,
                criteriaQuery,
                userRoot,
                User.class.getDeclaredFields()
        );
    }
}