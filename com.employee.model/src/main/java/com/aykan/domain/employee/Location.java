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

	// Veritabanýna tablolanmasýný istedigimiz classlarýmýz- modellerimiz - pojolarýmýz - beanlarýmýzý @Entity olarak tanýmlýyoruz.
	//Named query kullanarak sorgularýmýz compile sýrasýnda cachleniyor yani önbellge alýnýyor. Buda bize daha hýzlý sorgu yapmamýzý saglanýyor.
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name = "locationId", nullable = false, unique = true)
	private Long locationId;
	//Location tablomuzun primaryKey'ini Id annotation'ý ile belirtebiliriz.
	// Prýmarykey calýsma stratejisini Auto olarak belirledik.
	//Location nesnesi oluþtururken id degerini biz vermeyecegiz veritabanýna kayýt sýrasýnda auto olarak eklenecektir.
	//nullable boþ býrakýlýp býrakýlamayacagý belirlenir. Unique  benzersiz olup olmaycagý belirlenir.
	
	@Column(length = 60)
	private String streetAddress;
	//length veritabanýndaki tablonon o columnda kac karakter veri girilecegi ayarlanýr.
	
	private int postalCode;
	
	//@Column dipnotu kullanmasakta bizim yerimize hibernate column oluþturacaktýr.
	
	@Column(name = "city")
	private String city;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
	private List<Department> departments;
	//--OneToMany--
	//1 e n(cok) Bir tane Location birden fazla departman olabilir demek istiyoruz. 
	//Bu cok Department'i nerde tutacaz peki; Bir colection yapýsýnda tutacaz. Örnegin Location Adana olsun Adanada da 5 tane departman olsun iþte her location
	//için bir list tutacaz ve o listede o locationdaki departmenler olucak.
	
	//--Cascade--
	//Bu Locationda bir deðiþiklik yaptýgýmýzda ona baglý birimlerin yani buradaki örnekte department'lerde nasýl deðiþiklikler olacagýný belirtiyoruz.
	//Örnegin Location'ýnýmýzýn þehri Adana olsun Departmentlerimizde Software-Tester-Database. Adanada bulunan departmanlarý belirledik.
	//Eger cascade = CascadeType.MERGE yaptýgýmýzda, þehrimzin isimini güncelledimizde yani Adana'yý Bursa yaptýgmýzda adanada bulunan departmanlar artýk bursa'da
	//görünecek. Yani ben Software departmanýna detaylý bir þekilde inceledigimde Bursada oldugunu bana gösterecek.
	
	//Burada locationdaki güncelleme iþlemleri için o locationdaki bulunan departmetlerinde güncellemesine Casscade kullanarak izin verdik.
	//Eger bu location silersek bu Location'a baglý tüm department leride silme izni verdik. Eger vermezsek ve location silersek veritabaný hatasý alýrýz.
	
	//--mappedBy--
	//MappedBy kullanarak hangi classýmýzýn birinci sýnýf class oldugunu belirtebiliriz. buradaki örnekte department classýndaki location nesnesinin adýný verdim
	//nedeni birinci sýnýf class bizim icin department olsun istedim. ve oluþacak foreign key id'si department tablosunda oluþsun istedim
	//Location class
	//private Location location;
	//Location table:
	//locationId  --- city ---- ......
	//		1 		  Adana
	//		2		  Bursa
	
	//Department table:
	//departmentId --- departmentName --- locationId
	//	   100			software			1
	
	//Burada aslýnda birinci sýnýf clasýmýzý ve foreign keyimizin nerede tutulacagýný belirledik..
	
	
	
	
	//---------------------------------
	//Bir sorgu yazdýk bize Location getirecek.. 
	//entityManager.getTransaction().begin()-- Session actýk..
	//Location location = sorgu...
	//location.getDepartments(); 
	//entityManager.close(); -- Session kapattýk.
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
