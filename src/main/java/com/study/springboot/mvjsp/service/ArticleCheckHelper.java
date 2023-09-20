package com.study.springboot.mvjsp.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.mvjsp.dao.ArticleDao;
import com.study.springboot.mvjsp.model.Article;

@Service
public class ArticleCheckHelper {
	
	@Autowired
	ArticleDao articleDao;

	public Article checkExistsAndPassword(int articleId, String password) 
			        throws ArticleNotFoundException, InvalidPasswordException{

		Article article = articleDao.selectById(articleId);
		if (article == null) {
			throw new ArticleNotFoundException(
					"게시 글이 존재하지 않음: " + articleId);
		}
		if (!checkPassword(article.getPassword(), password)) {
			throw new InvalidPasswordException("패스워드 오류");
		}
		return article;
	}

	private boolean checkPassword(String realPassword, String userInputPassword) {
		if (realPassword == null) {
			return false;
		}
		if (realPassword.length() == 0) {
			return false;
		}
		return realPassword.equals(userInputPassword);
	}

}
