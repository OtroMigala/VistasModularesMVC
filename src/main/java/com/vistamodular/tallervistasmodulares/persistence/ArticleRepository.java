package com.vistamodular.tallervistasmodulares.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vistamodular.tallervistasmodulares.domain.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}