package vse.cz.vseblog.data.response;

import java.util.List;

/**
 * @author dusan.petren
 */
public class EventDetailResponseRST extends EventResponseRST {

	private static final long serialVersionUID = -3922606598831164450L;
	private List<CommentResponseRST> comments;

	public List<CommentResponseRST> getComments() {
		return comments;
	}

	public void setComments(List<CommentResponseRST> comments) {
		this.comments = comments;
	}
}
