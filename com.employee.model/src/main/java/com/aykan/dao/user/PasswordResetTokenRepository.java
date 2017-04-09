package com.aykan.dao.user;

import java.util.Date;
import java.util.List;

import com.aykan.domain.user.PasswordResetToken;
import com.aykan.domain.user.User;

public interface PasswordResetTokenRepository {

	PasswordResetToken savePasswordResetToken(PasswordResetToken passwordResetToken);
	
	PasswordResetToken updatePasswordResetToken(PasswordResetToken passwordResetToken);
	
	PasswordResetToken deletePasswordResetToken(PasswordResetToken passwordResetToken);
	
	PasswordResetToken findPasswordResetTokenByToken(String token);
	
	PasswordResetToken findPasswordResetTokenByUser(User user);
	
	PasswordResetToken findPasswordResetTokenFindById(Long id);
	
	//tarihi gecen tokenlerin listesini verir
	List<PasswordResetToken> findAllByExpiryDateLessThan(Date date);
	
	//@Scheduler annotation altýnda tanýmlayacaz sürekli komutumuz calýsacak
	boolean deleteAllExpiredSince(Date date);
}
