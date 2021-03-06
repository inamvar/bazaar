package com.offeronline.dao;

import java.util.List;

import com.offeronline.domain.Comment;
import com.offeronline.domain.Deal;

public interface CommentDao extends GenericDao<Comment>{

	List<Comment> findByDeal(Deal deal, boolean accepted);
	List<Comment> findAll(String sort,String asc);
	List<Comment> findAccepted(boolean accepted);
	void acceptAllComments();
	int changeAccept(int id, boolean accept);
}
