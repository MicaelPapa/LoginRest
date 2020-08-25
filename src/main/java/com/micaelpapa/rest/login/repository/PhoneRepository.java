package com.micaelpapa.rest.login.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.micaelpapa.rest.login.model.dao.Phone;
import com.micaelpapa.rest.login.model.dto.PhoneDTO;

@Repository
public class PhoneRepository {
	@PersistenceContext
	@Autowired
	EntityManager em;

	@Transactional
	public void registerPhone(Phone phone) {
		em.persist(phone);
	}

	@Transactional
	public Phone findPhone(PhoneDTO phoneDTO) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Phone> cq = cb.createQuery(Phone.class);
		Root<Phone> phone = cq.from(Phone.class);
		Predicate numberPredicate = cb.equal(phone.get("number"), phoneDTO.getNumber());
		Predicate cityCodePredicate = cb.equal(phone.get("citycode"), phoneDTO.getCitycode());
		Predicate countryPredicate = cb.equal(phone.get("countrycode"), phoneDTO.getCountrycode());

		cq.where(numberPredicate, cityCodePredicate, countryPredicate);
		TypedQuery<Phone> query = em.createQuery(cq);
		return query.getResultList().size() > 0 ? query.getResultList().get(0) : null;
	}

}
