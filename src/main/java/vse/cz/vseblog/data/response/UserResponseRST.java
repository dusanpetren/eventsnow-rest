package vse.cz.vseblog.data.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import vse.cz.vseblog.entity.EventEntity;

/**
 * @author dusan.petren
 */
public class UserResponseRST implements Serializable {

	private static final long serialVersionUID = 3852405002254555442L;

	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String gender;
	private String type;
	private java.sql.Timestamp createdAt;
	private java.sql.Timestamp editedAt;

	@JsonIgnore
	private List<EventResponseRST> posts;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getEditedAt() {
		return editedAt;
	}

	public void setEditedAt(Timestamp editedAt) {
		this.editedAt = editedAt;
	}

	public List<EventResponseRST> getPosts() {
		return posts;
	}

	public void setPosts(List<EventResponseRST> posts) {
		this.posts = posts;
	}
}
