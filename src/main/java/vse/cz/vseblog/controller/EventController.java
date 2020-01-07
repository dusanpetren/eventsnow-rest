package vse.cz.vseblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import vse.cz.vseblog.data.request.CreateEventRequestData;
import vse.cz.vseblog.data.request.PostCommentRequest;
import vse.cz.vseblog.data.request.SearchEventRequest;
import vse.cz.vseblog.data.response.CommentResponseRST;
import vse.cz.vseblog.data.response.EventResponseRST;
import vse.cz.vseblog.data.response.EventTypeResponseRST;
import vse.cz.vseblog.service.CommentService;
import vse.cz.vseblog.service.EventService;

/**
 * @author dusan.petren
 */
@Controller
@RequestMapping(value = "/event")
public class EventController {

	private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	@Autowired
	private EventService eventServiceImpl;

	@Autowired
	private CommentService commentServiceImpl;

	@RequestMapping(value = "/events", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<EventTypeResponseRST> getEventList(@RequestParam(value = "p") int page,
	                                                         @RequestParam(value = "s") int size) {
		logger.info("Getting events with paginating, for page #{} with pagesize {} ", page, size);
		return new ResponseEntity<>(eventServiceImpl.getAllEvents(page, size), HttpStatus.OK);
	}

	@RequestMapping(value = "/events/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<EventTypeResponseRST> getEventListForType(@PathVariable(value = "type") String type, @RequestParam(value = "p") int page,
	                                                                @RequestParam(value = "s") int size) {
		logger.info("Getting events with paginating, for page #{} with pagesize {} ", page, size);
		return new ResponseEntity<>(eventServiceImpl.getEventsByType(type,page, size), HttpStatus.OK);
	}

	@RequestMapping(value = "/events/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<EventTypeResponseRST> searchForEvent(@RequestParam(value = "p") int page,
	                                                           @RequestParam(value = "s") int size, @RequestBody SearchEventRequest searchEventRequest) {
		logger.info("Getting events with paginating, for page #{} with pagesize {} ", page, size);
		return new ResponseEntity<>(eventServiceImpl.seachEventByValue(page, size, searchEventRequest), HttpStatus.OK);
	}

	@RequestMapping(value = "/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<EventResponseRST> getEvent(@PathVariable(value = "eventId") int eventId) {
		logger.info("Getting event with Id: {}", eventId);

		return new ResponseEntity<>(eventServiceImpl.getEvent(eventId), HttpStatus.OK);
	}

	@RequestMapping(value = "/secured/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<EventResponseRST> createEvent(@RequestBody CreateEventRequestData createEventRequestData) {
		logger.info("Creating new event {}", createEventRequestData);

		return new ResponseEntity<>(eventServiceImpl.createEvent(createEventRequestData), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/secured/{eventId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public ResponseEntity<EventResponseRST> editEvent(@PathVariable(value = "eventId") Long eventId, @RequestBody CreateEventRequestData createEventRequestData) {
		logger.info("Creating new event {}", createEventRequestData);

		return new ResponseEntity<>(eventServiceImpl.editEvent(eventId, createEventRequestData), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/secured/{eventId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public ResponseEntity<String> deleteEvent(@PathVariable Long eventId) {
		this.eventServiceImpl.deleteEvent(eventId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/secured/comment/{commentId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
		this.eventServiceImpl.deleteComment(commentId);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/secured/{eventId}/comment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<CommentResponseRST> addComment(@PathVariable Long eventId, @RequestBody PostCommentRequest postCommentRequest) {

		return new ResponseEntity<>(this.commentServiceImpl.createComment(eventId, postCommentRequest), HttpStatus.CREATED);
	}
}
