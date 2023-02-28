package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.entities.Reaction;
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
public class ExtendReactionRepositoryImpl extends ExtendEntityRepositoryBase<Reaction> implements ExtendReactionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final String ERROR_INVALID_PARAMETER = "Tham số không hợp lệ";

    @Override
    public List<Reaction> searchReaction(Map<String, String> queries) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Reaction> criteriaQuery = criteriaBuilder.createQuery(Reaction.class);
        Root<Reaction> reactionRoot = criteriaQuery.from(Reaction.class);

        return this.dynamicSearchEntity(
                this.entityManager,
                queries,
                criteriaBuilder,
                criteriaQuery,
                reactionRoot,
                Reaction.class.getDeclaredFields()
        );
    }
}