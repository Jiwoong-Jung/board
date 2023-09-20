package com.study.springboot.mvjsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.mvjsp.dao.ArticleDao;
import com.study.springboot.mvjsp.model.Article;

@Service
public class ReadArticleService {
	
	@Autowired
	ArticleDao articleDao;

	public Article readArticle(int articleId) throws ArticleNotFoundException {
		return selectArticle(articleId, true);
	}

	private Article selectArticle(int articleId, boolean increaseCount) throws ArticleNotFoundException {

			Article article = articleDao.selectById(articleId);
			if (article == null) {
				throw new ArticleNotFoundException(
						"게시글이 없음: " + articleId);
			}
			if (increaseCount) {
				articleDao.increaseReadCount( articleId);
				article.setReadCount(article.getReadCount() + 1);
			}
			return article;

	}

	public Article getArticle(int articleId) throws ArticleNotFoundException {
		return selectArticle(articleId, false);
	}
}
