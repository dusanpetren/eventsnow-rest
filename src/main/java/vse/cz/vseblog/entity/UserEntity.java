package vse.cz.vseblog.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "USER")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	private String username;

	private String password;

	private String firstname;

	private String lastname;

	private String gender;

	@Enumerated(EnumType.STRING)
	private UserType type;

	private String email;

	@Column(name = "created_at")
	@CreationTimestamp
	private Timestamp createdAt;

	@Column(name = "edited_at")
	@UpdateTimestamp
	private Timestamp editedAt;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<EventEntity> posts;


	public UserEntity() {
	}

	public UserEntity(UserEntity userEntity) {
		this.id = userEntity.getId();
		this.username = userEntity.getUsername();
		this.password = userEntity.getPassword();
		this.firstname = userEntity.getFirstname();
		this.lastname = userEntity.getLastname();
		this.email = userEntity.getEmail();
		this.createdAt = userEntity.getCreatedAt();
		this.editedAt = userEntity.getEditedAt();
		this.gender = userEntity.getGender();
		this.type = userEntity.getType();
		this.posts = userEntity.posts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public java.sql.Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(java.sql.Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getEditedAt() {
		return editedAt;
	}

	public void setEditedAt(Timestamp editedAt) {
		this.editedAt = editedAt;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public List<EventEntity> getPosts() {
		return posts;
	}

	public void setPosts(List<EventEntity> posts) {
		this.posts = posts;
	}

}
