package com.kalatag.dao;

import java.util.List;

import com.kalatag.domain.Comment;
import com.kalatag.domain.Deal;

public interface CommentDao extends GenericDao<Comment>{

	List<Comment> findByDeal(Deal deal, boolean accepted);
	List<Comment> findAll(String sort,String asc);
	int changeAccept(int id, boolean accept);
}
