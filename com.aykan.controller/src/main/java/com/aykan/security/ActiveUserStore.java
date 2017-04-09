package com.aykan.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ActiveUserStore {

	//Active olan userýn email'ini users listesine ekleyecegiz.
	//Sonra jsp sayfamýza listemizi gönderip aktif olan userlarý yazdýrabiliriz.
	
	private static List<LoggedUser> users;

	public ActiveUserStore() {
		// TODO Auto-generated constructor stub
		users = new ArrayList<>();
	}

	public static List<LoggedUser> getUsers() {
		return users;
	}

	public static void setUsers(List<LoggedUser> users) {
		ActiveUserStore.users = users;
	}

	//Nesnemiz cagýrýldýgýnda users listesi oluþturulur.

}
