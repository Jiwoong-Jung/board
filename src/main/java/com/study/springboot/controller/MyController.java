package com.study.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.jdbc.MyTestDao;
import com.study.springboot.jdbc.MyUserDTO;
import com.study.springboot.mvjsp.dao.ArticleDao;
import com.study.springboot.mvjsp.service.ArticleNotFoundException;
import com.study.springboot.mvjsp.service.IdGenerator;
import com.study.springboot.mvjsp.service.ListArticleService;
import com.study.springboot.mvjsp.service.ReadArticleService;
import com.study.springboot.spring.TestDao;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MyController {
	
	@Autowired
	ArticleDao articleDao;
	
	@Autowired
	TestDao testDao;
	
	@Autowired
	MyTestDao myTestDao;
	
	@Autowired
	IdGenerator idgenerator;
	
	@Autowired
	ListArticleService listArticleService;
	
	@Autowired
	ReadArticleService readArticleService;
	
	@GetMapping("/main")
	public String main() {
		
//		log.info("-----테스트: {}", testDao.count());
//		log.info("-----마이바티스 테스트: {}", myTestDao.myCount());
//		log.info("-----마이바티스 테스트: {}", articleDao.selectCount());
		
//		Article article = Article.builder().groupId(1)
//				.sequenceNumber("0000000005999999").writerName("홍길수")
//				.password("1234").title("길수의 글제목").content("길수의 글내용").build();
//		articleDao.insert(article);
//		log.info("-----입력 후 리턴 번호: {}", article.getId());
//		log.info("-----마이바티스 테스트: {}", articleDao.select(1, 7));
//		System.out.println(articleDao.select(1, 3));
//		log.info("-----마이바티스 테스트: {}", articleDao.selectById(7));
//		articleDao.increaseReadCount(7);
//		log.info("-----마이바티스 테스트: {}", articleDao.selectById(7));
		
//		log.info("-----마이바티스 테스트: {}", articleDao.selectLastSequenceNumber("0000000005999999", "0000000001999999"));
		
//		Article article2 = Article.builder().id(5).title("길수의 글제목").content("길수의 글내용").build();
//		articleDao.delete(5);
//		log.info("-----마이바티스 테스트: {}", articleDao.select(1, 7));
		
//		log.info("-----마이바티스 테스트: {}", idgenerator.generateNextId("article"));
		
//		log.info("-----마이바티스 테스트: {}", listArticleService.getArticleList(1));
		
		try {
			log.info("-----마이바티스 테스트: {}", readArticleService.getArticle(8));
		} catch (ArticleNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "main";
	}
	
	@GetMapping("/list123")
	@ResponseBody
	public List<MyUserDTO> list() {
		return myTestDao.list();
	}

}
