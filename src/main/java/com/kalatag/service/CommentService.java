package com.kalatag.service;

import java.util.List;

import com.kalatag.domain.Comment;
import com.kalatag.domain.Deal;

public interface CommentService extends CRUDService<Comment> {

	List<Comment> findByDeal(Deal deal, boolean accepted);
	int changeAccept(int id, boolean accept);
	List<Comment> findAll(String sort,String asc);
	List<Comment> findAccepted(boolean accepted);
	 void acceptAllComments();
}
