package com.micaelpapa.rest.login.repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import com.micaelpapa.rest.login.model.dao.AppUser;

@Repository
public class UserRepository {
	@Autowired
	EntityManager em;

	@Transactional
	public void persistAppUser(AppUser user) throws PersistenceException {
		em.persist(user);
	}

	@Transactional
	public AppUser findAppUser(String mail) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AppUser> cq = cb.createQuery(AppUser.class);
		Root<AppUser> user = cq.from(AppUser.class);
		Predicate mailPredicate = cb.equal(user.get("mail"), mail);
		cq.where(mailPredicate);
		TypedQuery<AppUser> query = em.createQuery(cq);
		return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
	}
}
