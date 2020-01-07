package vse.cz.vseblog.data.request;

import java.io.Serializable;

/**
 * Popis
 *
 * @author dusan.petren
 */
public class PostCommentRequest implements Serializable {

	private static final long serialVersionUID = -1122491238213865185L;

	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
