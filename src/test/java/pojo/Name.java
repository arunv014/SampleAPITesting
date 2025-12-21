package pojo;

public class Name {
	private String firstname;
	private String lastname;
	
	public Name(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstName() {
		return firstname;
	}
	
	public void setFirstName(String firstname) {
		this.firstname=firstname;
	}
	
	public String getlastname() {
		return lastname;
	}
	
	public void setLastName(String lastname) {
		this.lastname=lastname;
	}

}
