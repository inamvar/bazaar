package com.offeronline.service;

import java.util.List;

import com.offeronline.domain.Comment;
import com.offeronline.domain.Deal;

public interface CommentService extends CRUDService<Comment> {

	List<Comment> findByDeal(Deal deal, boolean accepted);
	int changeAccept(int id, boolean accept);
	List<Comment> findAll(String sort,String asc);
	List<Comment> findAccepted(boolean accepted);
	 void acceptAllComments();
}
