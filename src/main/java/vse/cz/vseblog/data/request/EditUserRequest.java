package vse.cz.vseblog.data.request;

import java.io.Serializable;

/**
 * @author dusan.petren
 */
public class EditUserRequest implements Serializable {


	private static final long serialVersionUID = 8273237818865530587L;

	private String firstname;
	private String lastname;
	private String email;
	private String gender;
	private String type;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "EditUserRequest{" +
				"firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
