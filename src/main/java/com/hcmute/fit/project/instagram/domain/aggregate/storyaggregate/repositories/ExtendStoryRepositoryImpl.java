package com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.entities.Story;
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
public class ExtendStoryRepositoryImpl extends ExtendEntityRepositoryBase<Story> implements ExtendStoryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final String ERROR_INVALID_PARAMETER = "Tham số không hợp lệ";

    @Override
    public List<Story> searchStory(Map<String, String> queries) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Story> criteriaQuery = criteriaBuilder.createQuery(Story.class);
        Root<Story> storyRoot = criteriaQuery.from(Story.class);

        return this.dynamicSearchEntity(
                this.entityManager,
                queries,
                criteriaBuilder,
                criteriaQuery,
                storyRoot,
                Story.class.getDeclaredFields()
        );
    }
}