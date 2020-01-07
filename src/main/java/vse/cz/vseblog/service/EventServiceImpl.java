package vse.cz.vseblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;
import vse.cz.vseblog.data.request.CreateEventRequestData;
import vse.cz.vseblog.data.request.SearchEventRequest;
import vse.cz.vseblog.data.response.EventDetailResponseRST;
import vse.cz.vseblog.data.response.EventResponseRST;
import vse.cz.vseblog.data.response.EventTypeResponseRST;
import vse.cz.vseblog.entity.EventEntity;
import vse.cz.vseblog.entity.EventType;
import vse.cz.vseblog.entity.UserEntity;
import vse.cz.vseblog.mapper.CommentDataMapper;
import vse.cz.vseblog.mapper.CreateEventMapper;
import vse.cz.vseblog.mapper.EventDetailMapper;
import vse.cz.vseblog.mapper.EventMapper;
import vse.cz.vseblog.repository.CommentRepository;
import vse.cz.vseblog.repository.EventRepository;
import vse.cz.vseblog.repository.UserRepository;
import vse.cz.vseblog.util.JwtTokenUtil;

/**
 * @author dusan.petren
 *
 */
@Service
public class EventServiceImpl implements EventService {


	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private CreateEventMapper createEventMapper;

	@Autowired
	private EventMapper eventMapper;

	@Autowired
	private EventDetailMapper eventDetailMapper;

	@Autowired
	private CommentDataMapper commentDataMapper;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;


	@Override
	public EventTypeResponseRST getAllEvents(int page, int size) {
		EventTypeResponseRST response = new EventTypeResponseRST();
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		response.setEventsList(eventRepository.findAll(firstPageWithTwoElements).stream()
				.map(entity -> eventMapper.destinationToSource(entity))
				.collect(Collectors.toList()));

		Long numberOfEvents = eventRepository.count();
		response.setAvailablePages((int) Math.ceil((numberOfEvents.floatValue() / size)));
		return response;
	}

	@Override
	public EventTypeResponseRST getEventsByType(String type, int page, int size) {
		EventType eventype = EventType.valueOf(type);
		Pageable pageable = PageRequest.of(page, size);
		EventTypeResponseRST response = new EventTypeResponseRST();
		response.setEventsList(eventRepository.findAllByType(eventype, pageable).stream()
				.map(entity -> eventMapper.destinationToSource(entity))
				.collect(Collectors.toList()));

		Integer numberOfEvents = eventRepository.countAllByType(eventype);
		response.setAvailablePages((int) Math.ceil((numberOfEvents.floatValue() / size)));
		return response;
	}

	@Override
	public EventTypeResponseRST seachEventByValue(int page, int size, SearchEventRequest searchEventRequest) {
		Pageable pageable = PageRequest.of(page, size);
		EventTypeResponseRST response = new EventTypeResponseRST();
		response.setEventsList(eventRepository.findAllBySearch(searchEventRequest.getSearchValue(), pageable).stream()
				.map(entity -> eventMapper.destinationToSource(entity))
				.collect(Collectors.toList()));

		Integer numberOfEvents = eventRepository.countAllBySearch(searchEventRequest.getSearchValue());
		response.setAvailablePages((int) Math.ceil((numberOfEvents.floatValue() / size)));
		return response;
	}

	@Override
	public EventDetailResponseRST getEvent(long eventId) {
		EventEntity eventEntity = eventRepository.findById(eventId).get();

		EventDetailResponseRST eventDetailResponseRST = eventDetailMapper.destinationToSource(eventEntity);
		eventDetailResponseRST.setComments(eventEntity.getComments().stream()
				.map(commentEntity -> commentDataMapper.destinationToSource(commentEntity))
				.collect(Collectors.toList()));
		return eventDetailResponseRST;
	}

	@Override
	public EventResponseRST createEvent(CreateEventRequestData createEventRequestData) {
		String username = jwtTokenUtil.getUsername();

		EventEntity eventEntity = createEventMapper.sourceToDestination(createEventRequestData);

		UserEntity author = userRepository.findByUsername(username).get();

		eventEntity.setUser(author);

		return eventMapper.destinationToSource(eventRepository.save(eventEntity));
	}

	@Override
	public EventResponseRST editEvent(long eventId, CreateEventRequestData createEventRequestData) {
		EventEntity eventEntity = eventRepository.findById(eventId).get();

		if (createEventRequestData.getTitle() != null && !createEventRequestData.getTitle().isEmpty()){
			eventEntity.setTitle(createEventRequestData.getTitle());
		}
		if (createEventRequestData.getAuthor() != null && !createEventRequestData.getAuthor().isEmpty()){
			Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(createEventRequestData.getAuthor());

			if (!optionalUserEntity.isPresent()) {
				throw new IllegalArgumentException("Username not found");
			}
			eventEntity.setUser(optionalUserEntity.get());
		}
		if (createEventRequestData.getContent() != null && !createEventRequestData.getContent().isEmpty()){
			eventEntity.setContent(createEventRequestData.getContent());
		}
		if (createEventRequestData.getType() != null && !createEventRequestData.getType().isEmpty()){
			eventEntity.setType(EventType.valueOf(createEventRequestData.getType()));

		}
		if (createEventRequestData.getLatitude() != null && !createEventRequestData.getLatitude().isEmpty()) {
			eventEntity.setLatitude(createEventRequestData.getLatitude());

		}
		if (createEventRequestData.getLongitude() != null && !createEventRequestData.getLongitude().isEmpty()) {
			eventEntity.setLongitude(createEventRequestData.getLongitude());

		}
		if (createEventRequestData.getCreatedAt() != null) {
			eventEntity.setCreatedAt(createEventRequestData.getCreatedAt());

		}
		if (createEventRequestData.getEventTime() != null) {
			eventEntity.setEventTime(createEventRequestData.getEventTime());

		}

		EventDetailResponseRST eventDetailResponseRST = eventDetailMapper.destinationToSource(eventRepository.save(eventEntity));
		eventDetailResponseRST.setComments(eventEntity.getComments().stream()
				.map(commentEntity -> commentDataMapper.destinationToSource(commentEntity))
				.collect(Collectors.toList()));
		return eventDetailResponseRST;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteEvent(Long eventId) {
		eventRepository.deleteById(eventId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
	}


}
