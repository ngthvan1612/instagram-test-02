package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.entities.Post;
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
public class ExtendPostRepositoryImpl extends ExtendEntityRepositoryBase<Post> implements ExtendPostRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final String ERROR_INVALID_PARAMETER = "Tham số không hợp lệ";

    @Override
    public List<Post> searchPost(Map<String, String> queries) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
        Root<Post> postRoot = criteriaQuery.from(Post.class);

        return this.dynamicSearchEntity(
                this.entityManager,
                queries,
                criteriaBuilder,
                criteriaQuery,
                postRoot,
                Post.class.getDeclaredFields()
        );
    }
}