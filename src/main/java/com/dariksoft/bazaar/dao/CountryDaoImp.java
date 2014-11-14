package com.dariksoft.bazaar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.dariksoft.bazaar.domain.Country;

@Repository
public class CountryDaoImp implements CountryDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insert(Country country) {
		
		entityManager.persist(country);
	}

	@Override
	public int delete(Country country) {
		entityManager.remove(findbyId(country.getId()));
		return 0;
	}

	@Override
	public int delete(int id) {
		entityManager.remove(findbyId(id));
		return 0;
	}

	@Override
	public int update(Country country) {
		entityManager.merge(country);
		return 0;
	}

	@Override
	public Country findbyId(int id) {
		return entityManager.find(Country.class,id);
	
	}

	@Override
	public List<Country> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Country> cq = builder.createQuery(Country.class);
		Root<Country> root = cq.from(Country.class);
		cq.select(root);
		
		return   entityManager.createQuery(cq).getResultList();
	}

}
