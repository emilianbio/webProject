package com.aykan.security;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.springframework.stereotype.Component;

import com.aykan.security.aop.Loggable;


@Component
public class LoggedUser implements HttpSessionBindingListener{

	private String username;
	private String date;

	public LoggedUser() {
		// TODO Auto-generated constructor stub
	}
	
	public LoggedUser(String username, String date) {
		this.date = date;
		this.username = username;
	}
	//Seneryo;
	//HttpSessionBindingListener implement oldugu class�n nesnesi bir sessiona
	//eklengidini dinleyince veya duyunca valueBound nesnesi cal�s�r.
	//Sessiona eklenen nesneyi event.getValue() ile �ekebiliriz.
	//Cektikten sonra user nesnesinin emailini aktif olan userlar�n listesine ekledik.
	//Bu listeyi daha sonra model.attribute veya baska birsey kullanarak jsp sayfam�za g�nderebiliriz.
	@Override
	@Loggable
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		System.out.println("HttpBindig listener dinledi valueBound");
		
		List<LoggedUser> userList = ActiveUserStore.getUsers();
		LoggedUser user =(LoggedUser) event.getValue();
		if(!userList.contains(user)){
			userList.add(user);
		}
		ActiveUserStore.setUsers(userList);
		/*if(!users.contains(user.getEmail())){
			users.add(user.getEmail());
		}*/
	}
	
	
	//HttpSessionBindingListener implement oldugu class'�n nesnesi bir sessiondan silindiginde buras� cal�s�r. 
	@Override
	@Loggable
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		System.out.println("HttpBindig listener dinledi valueUnBound");
		List<LoggedUser> userList = ActiveUserStore.getUsers();
		LoggedUser user = (LoggedUser)event.getValue();
		if(userList.contains(user)){
			userList.remove(user);
		}
		ActiveUserStore.setUsers(userList);
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
