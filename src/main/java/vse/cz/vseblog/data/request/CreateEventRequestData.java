package vse.cz.vseblog.data.request;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author dusan.petren
 */
public class CreateEventRequestData implements Serializable {

	private static final long serialVersionUID = 9205090093254768307L;

	private String title;
	private String content;
	private String type;
	private String author;
	private String latitude;
	private String longitude;
	private Timestamp eventTime;
	private Timestamp createdAt;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Timestamp getEventTime() {
		return eventTime;
	}

	public void setEventTime(Timestamp eventTime) {
		this.eventTime = eventTime;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}


	@Override
	public String toString() {
		return "CreateEventRequestData{" +
				"title='" + title + '\'' +
				", content='" + content + '\'' +
				", type='" + type + '\'' +
				", author='" + author + '\'' +
				", latitude='" + latitude + '\'' +
				", longitude='" + longitude + '\'' +
				", eventTime=" + eventTime +
				", createdAt=" + createdAt +
				'}';
	}
}
