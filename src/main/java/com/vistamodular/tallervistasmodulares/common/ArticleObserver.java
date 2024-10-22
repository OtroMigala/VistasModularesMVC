package com.vistamodular.tallervistasmodulares.common;

import com.vistamodular.tallervistasmodulares.domain.Article;

public interface ArticleObserver {
    void update(Article article);
}