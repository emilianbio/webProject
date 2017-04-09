package com.aykan.task;

import java.time.Instant;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aykan.service.user.PasswordResetTokenService;
import com.aykan.service.user.VerificationTokenService;

@Component
public class TokensPugeTask {

	private static final Logger LOGGER = LogManager.getLogger(TokensPugeTask.class);

	@Autowired
	private VerificationTokenService verificationTokenService;
	
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;
	
	@Scheduled(cron = "0 0 5 * * ?")
	public void purgeExpired() {
		Date date = Date.from(Instant.now());
		
		passwordResetTokenService.deleteAllExpiredSince(date);
		
		verificationTokenService.deleteAllExpiredSince(date);
		
		
		LOGGER.info("Purge - Expired "  +date);
	}
	//	"0 0 5 * * ?" = hergün sabah 5 de calýsacak	
	//	"0 0 * * * *" = the top of every hour of every day.
	//	"*/10 * * * * *" = every ten seconds.
	//	"0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day. // - oldugunda aradaki aralýk lardada calýsýyor
	//	"0 * 6,19 * * *" = 6:00 AM and 7:00 PM every day.    // , oldugunda belitilen saatler de calýsýtor
	//	"0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
	//	"0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
	//	"0 0 0 25 12 ?" = every Christmas Day at midnight
		
	
	// @Scheduled(cron="*/5 * * * * ?")
	// public void demoServiceMethod()
	// {
	// System.out.println("Method executed at every 5 seconds. Current time is
	// :: "+ new Date());
	// }
}
