package vse.cz.vseblog.service;

import vse.cz.vseblog.data.request.PostCommentRequest;
import vse.cz.vseblog.data.response.CommentResponseRST;

/**
 * Service responsible for Comments
 *
 * @author dusan.petren
 */
public interface CommentService {

	/**
	 * Removes comment by id
	 */
	void deleteComment(Long commendId);

	/**
	 * Method for creating new comment on event
	 *
	 * @param eventId
	 * @param postCommentRequest
	 */
	CommentResponseRST createComment(Long eventId, PostCommentRequest postCommentRequest);}
