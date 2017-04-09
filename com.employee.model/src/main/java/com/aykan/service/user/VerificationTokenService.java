package com.aykan.service.user;

import java.util.Date;
import java.util.List;

import com.aykan.domain.user.User;
import com.aykan.domain.user.VerificationToken;

public interface VerificationTokenService {
	
	String validateVerificationToken(String token);
	
	VerificationToken saveVerificationToken(VerificationToken verificationToken);
	
	VerificationToken updateVerificationToken(VerificationToken verificationToken);
	
	VerificationToken deleteVerificationToken(VerificationToken verificationToken);
	
	VerificationToken findVerificationTokenByToken(String token);
	
	VerificationToken findVerificationTokenByUser(User user);
	
	//tarihi gecen tokenlerin listesini verir
	List<VerificationToken> findAllByExpiryDateLessThan(Date date);
	
	//@Scheduler annotation altýnda tanýmlayacaz sürekli komutumuz calýsacak
	boolean deleteAllExpiredSince(Date date);
}
