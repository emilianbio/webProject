package com.aykan.security;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class LoginAttemptService {

	private final int MAX_ATTEMPT = 3;
	
	private LoadingCache<String, Integer> loadingCache;
	

	private final Logger LOGGER = LogManager.getLogger();
	
	public LoginAttemptService() {
		// TODO Auto-generated constructor stub
	
		loadingCache = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.MINUTES).build(new CacheLoader<String, Integer>(){
			@Override
			public Integer load(String key) throws Exception {
				// TODO Auto-generated method stub
				return 0;
			}
		});
	}
	
	public void loginSucceded(String key){
		loadingCache.invalidate(key);
	}
	
	public void loginFailed(String key){
		int attempts = 0;
		LOGGER.warn("LOGÝN FAÝLED " + key);
		try {
			attempts = loadingCache.get(key);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			attempts = 0;
		}
			attempts++;
			loadingCache.put(key, attempts);
	}
	public boolean isBlocked(String key){
		try {
			return loadingCache.get(key) >= MAX_ATTEMPT;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}
}
