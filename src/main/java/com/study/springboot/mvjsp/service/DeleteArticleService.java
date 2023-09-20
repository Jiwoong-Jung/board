package com.study.springboot.mvjsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.mvjsp.dao.ArticleDao;

@Service
public class DeleteArticleService {

	@Autowired
	ArticleDao articleDao;
	
	public void deleteArticle(DeleteRequest deleteRequest)
			throws ArticleNotFoundException, InvalidPasswordException {

			ArticleCheckHelper checkHelper = new ArticleCheckHelper();
			checkHelper.checkExistsAndPassword(deleteRequest.getArticleId(),
					                             deleteRequest.getPassword());
			
			articleDao.delete(deleteRequest.getArticleId());

	}
}
