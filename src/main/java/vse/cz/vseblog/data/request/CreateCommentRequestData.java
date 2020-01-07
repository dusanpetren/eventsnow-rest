package vse.cz.vseblog.data.request;

import java.io.Serializable;

/**
 * @author dusan.petren
 */
public class CreateCommentRequestData implements Serializable {

	private static final long serialVersionUID = 7555276199102952856L;

	private Long postId;
	private String content;


	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CreateCommentRequestData{" +
				"postId=" + postId +
				", content='" + content + '\'' +
				'}';
	}
}
