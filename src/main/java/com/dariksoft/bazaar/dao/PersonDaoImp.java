package com.dariksoft.bazaar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.dariksoft.bazaar.domain.Person;

@Repository
public class PersonDaoImp implements PersonDao {

	@PersistenceContext
	private EntityManager entityManager;



	public void insert(Person person) {
		entityManager.persist(person);
		
	}

	public int delete(Person person) {
		entityManager.remove(findbyId(person.getId()));
		return 0;
	}
	
	public int delete(int id) {
		entityManager.remove(findbyId(id));
		return 0;
	}

	public List<Person> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> cq = builder.createQuery(Person.class);
		Root<Person> root = cq.from(Person.class);
		cq.select(root);
		
		return entityManager.createQuery(cq).getResultList();
	}

	public int update(Person person) {
		entityManager.merge(person);
		return 0;
	}

	
	public Person findbyId(int id) {		
		return entityManager.find(Person.class,id);
	}
	
}
