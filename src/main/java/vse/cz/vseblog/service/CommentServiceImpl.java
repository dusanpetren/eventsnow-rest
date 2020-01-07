package vse.cz.vseblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vse.cz.vseblog.data.request.PostCommentRequest;
import vse.cz.vseblog.data.response.CommentResponseRST;
import vse.cz.vseblog.entity.CommentEntity;
import vse.cz.vseblog.entity.EventEntity;
import vse.cz.vseblog.entity.UserEntity;
import vse.cz.vseblog.mapper.CommentDataMapper;
import vse.cz.vseblog.repository.CommentRepository;
import vse.cz.vseblog.repository.EventRepository;
import vse.cz.vseblog.repository.UserRepository;
import vse.cz.vseblog.util.JwtTokenUtil;

/**
 * @author dusan.petren
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentDataMapper commentDataMapper;

	@Override
	public void deleteComment(Long commendId) {
		this.commentRepository.deleteById(commendId);
	}


	@Override
	public CommentResponseRST createComment(Long eventId, PostCommentRequest postCommentRequest) {
		CommentEntity commentEntity = new CommentEntity();
		commentEntity.setContent(postCommentRequest.getComment());

		EventEntity eventEntity = eventRepository.findById(eventId).get();
		commentEntity.setEvent(eventEntity);

		String username = jwtTokenUtil.getUsername();
		UserEntity author = userRepository.findByUsername(username).get();

		commentEntity.setUser(author);

		return commentDataMapper.destinationToSource(commentRepository.save(commentEntity));
	}
}
