package com.kalatag.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kalatag.domain.Comment;
import com.kalatag.domain.Deal;

@Repository
public class CommentDaoImp extends GenericDaoImp<Comment> implements CommentDao {

	@Autowired
	SessionFactory sessionFactory;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> findByDeal(Deal deal, boolean accepted) {
		List<Comment> results = new ArrayList<Comment>();
		String hql = "FROM  Comment C WHERE C.deal = :deal and C.isAccepted = :accepted order by C.date DESC";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("deal", deal);
		query.setParameter("accepted", accepted);
		results = query.list();

		return results;
	}

}
