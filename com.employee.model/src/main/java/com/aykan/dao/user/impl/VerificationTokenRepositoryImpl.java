package com.aykan.dao.user.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aykan.dao.user.VerificationTokenRepository;
import com.aykan.domain.user.User;
import com.aykan.domain.user.VerificationToken;

@Repository
@Transactional
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public VerificationToken saveVerificationToken(VerificationToken verificationToken) {
		// TODO Auto-generated method stub
		entityManager.persist(verificationToken);
		return verificationToken;
	}

	@Override
	public VerificationToken updateVerificationToken(VerificationToken verificationToken) {
		// TODO Auto-generated method stub
		VerificationToken updatedToken = entityManager.merge(verificationToken);
		entityManager.flush();
		return updatedToken;
	}

	@Override
	public VerificationToken deleteVerificationToken(VerificationToken verificationToken) {
		// TODO Auto-generated method stub
		if(entityManager.contains(verificationToken)){
			entityManager.remove(verificationToken);
			return verificationToken;
		}
		VerificationToken deleteToken = findVerificationTokenByToken(verificationToken.getToken());
		entityManager.remove(deleteToken);
		return deleteToken;
	}

	@Override
	public VerificationToken findVerificationTokenByToken(String token) {
		// TODO Auto-generated method stub
		System.out.println("dao token : " + token);
		if(token.trim().length() <= 0 || token == null){
			throw new RuntimeException("Invalid token");
		}	
		return entityManager.createNamedQuery("VerificationToken.findByToken", VerificationToken.class).setParameter("token", token).getSingleResult();
	}

	@Override
	public VerificationToken findVerificationTokenByUser(User user) {
		// TODO Auto-generated method stub
		if(user == null)
			return null;
		TypedQuery<VerificationToken> typedQuery = entityManager.createNamedQuery("VerificationToken.findByUserId", VerificationToken.class);
		typedQuery.setParameter("userId", user.getId());
		return typedQuery.getSingleResult();
	}

	@Override
	public List<VerificationToken> findAllByExpiryDateLessThan(Date date) {
		// TODO Auto-generated method stub
		TypedQuery<VerificationToken> typedQuery = entityManager.createNamedQuery("VerificationToken.findAllByExpiryDateLessThan", VerificationToken.class);
		typedQuery.setParameter("expiryDate", date);
		return typedQuery.getResultList();
	}

	@Override
	public boolean deleteAllExpiredSince(Date date) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNamedQuery("VerificationToken.deleteExpiryDateToken");
		int count = query.setParameter("date", date, TemporalType.TIMESTAMP).executeUpdate();
		return count != -1;
	}
	
}
