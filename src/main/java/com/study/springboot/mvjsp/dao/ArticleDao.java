package com.study.springboot.mvjsp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.study.springboot.mvjsp.model.Article;

@Mapper
public interface ArticleDao {
	
	@Select("select count(*) from article")
	int selectCount();
	
	@Select("select article_id, group_id, sequence_no, posting_date, "
			+ "read_count, writer_name, password, title, content from article "
			+ "order by sequence_no desc limit ${firstRow-1}, ${endRow - firstRow + 1}")
	 @Results(id="myResultId", value = {
	          @Result(property = "id", column = "article_id"),
	          @Result(property = "groupId", column = "group_id"),
	          @Result(property = "sequenceNumber", column = "sequence_no"),
	          @Result(property = "postingDate", column = "posting_date"),
	          @Result(property = "readCount", column = "read_count"),
	          @Result(property = "writerName", column = "writer_name")
	  }) //필드명과 컬럼명이 같으면 @Results 는 필요없다.
	List<Article> select(@Param("firstRow") int firstRow, @Param("endRow") int endRow);

	@Insert("insert into article "
			+ "(group_id, sequence_no, posting_date, read_count, "
			+ "writer_name, password, title, content) "
			+ "values (#{article.groupId}, #{article.sequenceNumber},"
			+ " now(), 0, #{article.writerName}, #{article.password},"
			+ " #{article.title}, #{article.content})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="article_id")
	int insert(@Param("article") Article article);
	
	@Select("select * from article where article_id = #{param1}")
	Article selectById(int articleId);
	
	@Update("update article set read_count = read_count + 1 where article_id = #{arg0}")
	void increaseReadCount(int articleId);
	
	@Select("select min(sequence_no) from article where sequence_no < #{param1} and sequence_no >= #{param2}")
	String selectLastSequenceNumber(String searchMaxSeqNum, String searchMinSeqNum);
	
	@Update("update article set title = #{article.title}, content = #{article.content} where article_id = #{article.id}")
	int update(@Param("article") Article article);
	
	@Delete("delete from article where article_id = #{param1}")
	void delete(int articleId);
}








