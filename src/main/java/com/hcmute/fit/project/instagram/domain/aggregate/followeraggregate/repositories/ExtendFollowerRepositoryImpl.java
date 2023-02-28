package com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.entities.Follower;
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
public class ExtendFollowerRepositoryImpl extends ExtendEntityRepositoryBase<Follower> implements ExtendFollowerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final String ERROR_INVALID_PARAMETER = "Tham số không hợp lệ";

    @Override
    public List<Follower> searchFollower(Map<String, String> queries) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Follower> criteriaQuery = criteriaBuilder.createQuery(Follower.class);
        Root<Follower> followerRoot = criteriaQuery.from(Follower.class);

        return this.dynamicSearchEntity(
                this.entityManager,
                queries,
                criteriaBuilder,
                criteriaQuery,
                followerRoot,
                Follower.class.getDeclaredFields()
        );
    }
}