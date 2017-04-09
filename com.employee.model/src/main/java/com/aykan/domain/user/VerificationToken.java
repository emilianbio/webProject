package com.aykan.domain.user;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "VerificationToken")
@NamedQueries({
	@NamedQuery(name="VerificationToken.findByToken", query="SELECT v FROM VerificationToken v WHERE v.token = :token"),
	@NamedQuery(name="VerificationToken.findByUserId", query="SELECT v FROM VerificationToken v WHERE v.user.id = :userId"),
	@NamedQuery(name="VerificationToken.deleteExpiryDateToken", query="DELETE FROM VerificationToken v WHERE v.expiryDate <= :date"),
	
	
	@NamedQuery(name="VerificationToken.findAllByExpiryDateLessThan", query="SELECT v FROM VerificationToken v WHERE v.expiryDate <= :expiryDate" )
})
public class VerificationToken {

	@Transient
	private final int EXPIRY_DATE = 60*24;
	//@Transient olan property veritabanýna yansýmaz
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "verificationTokenId")
	private Long id;
	
	//UniDrectional
	@OneToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	@JoinColumn(name="userId")
	private User user;
	
	@Column(length=60)
	private String token;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;

	
	public VerificationToken() {
		this.expiryDate = calculateExpiryDate(EXPIRY_DATE);
	}
	
	public VerificationToken(String token, User user) {
		this.token = token;
		this.user = user;
		this.expiryDate = calculateExpiryDate(EXPIRY_DATE);
		
	}
	//Senaryo:
	//Kullanýcý kayýt oldugunda bir verificationToken nesnesi oluþturulacak. .
	//Oluþturulan 24 saatlik token ve kullanýcý id'si kullanýcýnýn email'ina gönderilen url'de parametre olarak tutulacak.
	//Kullanýcý email'deki url'e týkladýgýnda o url yakalayacak bir method yazacaz ve o url'deki token ve kullanýcýId'sini cekecegiz
	//Kullanýcý id ve token VerificationToken tablosunda aratýlacak
	//Token bulunursa o tokenin expiryDate'ne bakýlacak tokenin son kullanma tarihi gecmemiþ ise url'e parametre olarak verilen userId ile User tablosundan
	//user cekilip enabled'ýný true yapýp kullanýcý aktif edicegiz.
	
	//VerificationToken table:
	//	id		token		expiryDate		userId 
	//	1		asd567das	 tarih			  12	
	//	2		qewqwedqwe	 tarih			  15		
	
	
	private Date calculateExpiryDate(int EXPIRY_DATE) {
		Calendar calendar = Calendar.getInstance(); 
		// Bir takvim olustur.
		calendar.setTimeInMillis(new Date().getTime()); 
		//Takvime suanki tarihi verdik. Long cinsinden
		calendar.add(Calendar.MINUTE, EXPIRY_DATE); 
		//Takvime süre ekliyoruz. Ýlk hangi cinsten ekleyecegimizi seciyoruz sonra süremizi ekliyoruz
		return new Date(calendar.getTime().getTime());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}
