package com.kalatag.service;

import java.util.List;

import com.kalatag.domain.Comment;
import com.kalatag.domain.Deal;

public interface CommentService extends CRUDService<Comment> {

	List<Comment> findByDeal(Deal deal, boolean accepted);
}
