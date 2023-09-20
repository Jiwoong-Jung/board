package com.study.springboot.mvjsp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.study.springboot.mvjsp.model.Article;
import com.study.springboot.mvjsp.model.ArticleListModel;
import com.study.springboot.mvjsp.service.ArticleNotFoundException;
import com.study.springboot.mvjsp.service.ListArticleService;
import com.study.springboot.mvjsp.service.ReadArticleService;

@Controller
public class MvController {
	
	@Autowired
	ListArticleService listSerivce;
	
	@Autowired
	ReadArticleService readArticleService;
	
	@GetMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		String pageNumberString = request.getParameter("p");
		int pageNumber = 1;
		if (pageNumberString != null && pageNumberString.length() > 0) {
			pageNumber = Integer.parseInt(pageNumberString);
		}
		ArticleListModel articleListModel = listSerivce.getArticleList(pageNumber);
		request.setAttribute("listModel", articleListModel);
		
		if (articleListModel.getTotalPageCount() > 0) {
			int beginPageNumber = 
				(articleListModel.getRequestPage() - 1) / 10 * 10 + 1;
			int endPageNumber = beginPageNumber + 9;
			if (endPageNumber > articleListModel.getTotalPageCount()) {
				endPageNumber = articleListModel.getTotalPageCount();
			}
			model.addAttribute("beginPage", beginPageNumber);
			model.addAttribute("endPage", endPageNumber);
		}
		return "/list_view";
	}
	
	@GetMapping("/read")
	public String read(HttpServletRequest request, Model model) {
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		String viewPage = null;
		try {
			Article article = readArticleService.readArticle(articleId);
			request.setAttribute("article", article);
			viewPage = "/read_view";
		} catch(ArticleNotFoundException ex) {
			viewPage = "/article_not_found";
		}
		return viewPage;
	}

}
