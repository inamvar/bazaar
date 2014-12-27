package com.kalatag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kalatag.dao.CommentDao;
import com.kalatag.domain.Comment;
import com.kalatag.domain.Deal;

@Service
public class CommentServiceImp extends CRUDServiceImp<Comment> implements
		CommentService {

	@Autowired
	CommentDao commentDao;

	@Override
	@Transactional
	public List<Comment> findByDeal(Deal deal, boolean accepted) {
		return commentDao.findByDeal(deal, accepted);
	}

}
