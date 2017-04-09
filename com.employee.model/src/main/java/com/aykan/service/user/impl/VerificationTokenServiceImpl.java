package com.aykan.service.user.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aykan.dao.user.VerificationTokenRepository;
import com.aykan.domain.user.User;
import com.aykan.domain.user.VerificationToken;
import com.aykan.service.user.UserService;
import com.aykan.service.user.VerificationTokenService;

@Service
@Transactional
public class VerificationTokenServiceImpl implements VerificationTokenService{
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	
    @Autowired
    private UserService userService;
    
    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";
    
	@Override
	public String validateVerificationToken(String token) {
		VerificationToken verificationToken = verificationTokenRepository.findVerificationTokenByToken(token);
		if(verificationToken == null){
			return TOKEN_INVALID;
		}
		
		User user = verificationToken.getUser();
		Calendar calendar = Calendar.getInstance();
		if(verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime() <= 0){
			return TOKEN_EXPIRED;
		}
		
		user.setEnabled(true);
		
		userService.updateUser(user);
		
		deleteVerificationToken(verificationToken);
		
		return TOKEN_VALID;
	
	}
    
	@Override
	public VerificationToken saveVerificationToken(VerificationToken verificationToken) {
		return verificationTokenRepository.saveVerificationToken(verificationToken);
	}

	@Override
	public VerificationToken updateVerificationToken(VerificationToken verificationToken) {
		return verificationTokenRepository.updateVerificationToken(verificationToken);
	}

	@Override
	public VerificationToken deleteVerificationToken(VerificationToken verificationToken) {
		return verificationTokenRepository.deleteVerificationToken(verificationToken);
	}

	@Override
	public VerificationToken findVerificationTokenByToken(String token) {
		return verificationTokenRepository.findVerificationTokenByToken(token);
	}

	@Override
	public VerificationToken findVerificationTokenByUser(User user) {
		return verificationTokenRepository.findVerificationTokenByUser(user);
	}

	@Override
	public List<VerificationToken> findAllByExpiryDateLessThan(Date date) {
		return verificationTokenRepository.findAllByExpiryDateLessThan(date);
	}

	@Override
	public boolean deleteAllExpiredSince(Date date) {
		return verificationTokenRepository.deleteAllExpiredSince(date);
	}

}
