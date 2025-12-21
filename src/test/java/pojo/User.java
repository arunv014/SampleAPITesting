package pojo;

/*{
	"id": 1, 
	"email": "user@example.com", 
	"username": "username", 
	"password": "hashed_password", 
	"name": { 
		"firstname": "First", 
		"lastname": "Last" 
		}, 
	"address": { 
		"city": "City", 
		"street": "Street", 
		"number": 1, 
		"zipcode": "12345", 
		"geolocation": { 
			"lat": "0.0000", 
			"long": "0.0000" 
			} 
	}, 
	"phone": "123-456-7890" 
} */

public class User {
	
	//encapsulation
	
	private String email;
	private String username;
	private String password;
	private Name name;
	private Address address;
	private String phone;
	
	public User(String email, String username, String password, Name name, Address address, String phone) {
		this.email=email;
		this.username=username;
		this.password=password;
		this.name=name;
		this.address=address;
		this.phone=phone;
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	

}
