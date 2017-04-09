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
	//@Transient olan property veritaban�na yans�maz
	
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
	//Kullan�c� kay�t oldugunda bir verificationToken nesnesi olu�turulacak. .
	//Olu�turulan 24 saatlik token ve kullan�c� id'si kullan�c�n�n email'ina g�nderilen url'de parametre olarak tutulacak.
	//Kullan�c� email'deki url'e t�klad�g�nda o url yakalayacak bir method yazacaz ve o url'deki token ve kullan�c�Id'sini cekecegiz
	//Kullan�c� id ve token VerificationToken tablosunda arat�lacak
	//Token bulunursa o tokenin expiryDate'ne bak�lacak tokenin son kullanma tarihi gecmemi� ise url'e parametre olarak verilen userId ile User tablosundan
	//user cekilip enabled'�n� true yap�p kullan�c� aktif edicegiz.
	
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
		//Takvime s�re ekliyoruz. �lk hangi cinsten ekleyecegimizi seciyoruz sonra s�remizi ekliyoruz
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
