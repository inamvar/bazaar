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


	@Override
	public int changeAccept(int id, boolean accept) {
		String hql = "UPDATE  Comment C  SET C.isAccepted = :accept WHERE C.id= :id";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("accept", accept);
		query.setParameter("id", id);
		return query.executeUpdate();
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Comment> findAll(String sort, String asc) {
		List<Comment> results = new ArrayList<Comment>();
		String hql = "FROM  Comment C  order by C."+sort+" "+asc;
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		results = query.list();
		return results;
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<Comment> findAccepted(boolean accepted) {
		List<Comment> results = new ArrayList<Comment>();
		String hql = "FROM  Comment C  where C.isAccepted= :accepted order by C.date desc";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("accepted", accepted);
		results = query.list();
		if(results == null)
			 results = new ArrayList<Comment>();
		return results;
	}


	@Override

	public void acceptAllComments() {
		String hql = "UPDATE  Comment C  SET C.isAccepted = true WHERE C.isAccepted= false";
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
	}

}
