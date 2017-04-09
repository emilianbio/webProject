package com.aykan.domain.employee;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity 
@NamedQueries({
	@NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
	@NamedQuery(name = "Location.findDepartmentById", query = "SELECT l FROM Location l LEFT OUTER JOIN FETCH l.departments WHERE l.locationId = :locationId")
})
public class Location {

	// Veritaban�na tablolanmas�n� istedigimiz classlar�m�z- modellerimiz - pojolar�m�z - beanlar�m�z� @Entity olarak tan�ml�yoruz.
	//Named query kullanarak sorgular�m�z compile s�ras�nda cachleniyor yani �nbellge al�n�yor. Buda bize daha h�zl� sorgu yapmam�z� saglan�yor.
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name = "locationId", nullable = false, unique = true)
	private Long locationId;
	//Location tablomuzun primaryKey'ini Id annotation'� ile belirtebiliriz.
	// Pr�marykey cal�sma stratejisini Auto olarak belirledik.
	//Location nesnesi olu�tururken id degerini biz vermeyecegiz veritaban�na kay�t s�ras�nda auto olarak eklenecektir.
	//nullable bo� b�rak�l�p b�rak�lamayacag� belirlenir. Unique  benzersiz olup olmaycag� belirlenir.
	
	@Column(length = 60)
	private String streetAddress;
	//length veritaban�ndaki tablonon o columnda kac karakter veri girilecegi ayarlan�r.
	
	private int postalCode;
	
	//@Column dipnotu kullanmasakta bizim yerimize hibernate column olu�turacakt�r.
	
	@Column(name = "city")
	private String city;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
	private List<Department> departments;
	//--OneToMany--
	//1 e n(cok) Bir tane Location birden fazla departman olabilir demek istiyoruz. 
	//Bu cok Department'i nerde tutacaz peki; Bir colection yap�s�nda tutacaz. �rnegin Location Adana olsun Adanada da 5 tane departman olsun i�te her location
	//i�in bir list tutacaz ve o listede o locationdaki departmenler olucak.
	
	//--Cascade--
	//Bu Locationda bir de�i�iklik yapt�g�m�zda ona bagl� birimlerin yani buradaki �rnekte department'lerde nas�l de�i�iklikler olacag�n� belirtiyoruz.
	//�rnegin Location'�n�m�z�n �ehri Adana olsun Departmentlerimizde Software-Tester-Database. Adanada bulunan departmanlar� belirledik.
	//Eger cascade = CascadeType.MERGE yapt�g�m�zda, �ehrimzin isimini g�ncelledimizde yani Adana'y� Bursa yapt�gm�zda adanada bulunan departmanlar art�k bursa'da
	//g�r�necek. Yani ben Software departman�na detayl� bir �ekilde inceledigimde Bursada oldugunu bana g�sterecek.
	
	//Burada locationdaki g�ncelleme i�lemleri i�in o locationdaki bulunan departmetlerinde g�ncellemesine Casscade kullanarak izin verdik.
	//Eger bu location silersek bu Location'a bagl� t�m department leride silme izni verdik. Eger vermezsek ve location silersek veritaban� hatas� al�r�z.
	
	//--mappedBy--
	//MappedBy kullanarak hangi class�m�z�n birinci s�n�f class oldugunu belirtebiliriz. buradaki �rnekte department class�ndaki location nesnesinin ad�n� verdim
	//nedeni birinci s�n�f class bizim icin department olsun istedim. ve olu�acak foreign key id'si department tablosunda olu�sun istedim
	//Location class
	//private Location location;
	//Location table:
	//locationId  --- city ---- ......
	//		1 		  Adana
	//		2		  Bursa
	
	//Department table:
	//departmentId --- departmentName --- locationId
	//	   100			software			1
	
	//Burada asl�nda birinci s�n�f clas�m�z� ve foreign keyimizin nerede tutulacag�n� belirledik..
	
	
	
	
	//---------------------------------
	//Bir sorgu yazd�k bize Location getirecek.. 
	//entityManager.getTransaction().begin()-- Session act�k..
	//Location location = sorgu...
	//location.getDepartments(); 
	//entityManager.close(); -- Session kapatt�k.
	//location.getDepartments(); detached error...
	//--------------------------------------
	
	public Location() {
	}

	public Location(String streetAddress, int postalCode, String city) {
		this.streetAddress = streetAddress;
		this.postalCode = postalCode;
		this.city = city;
	}


	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	
}
