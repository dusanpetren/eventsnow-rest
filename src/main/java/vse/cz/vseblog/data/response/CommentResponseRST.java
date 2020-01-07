package vse.cz.vseblog.data.response;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author dusan.petren
 */
public class CommentResponseRST implements Serializable {

	private static final long serialVersionUID = -5725988973297548020L;

	private Long id;
	private String content;
	private String status;
	private java.sql.Timestamp createdAt;
	private String author;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
