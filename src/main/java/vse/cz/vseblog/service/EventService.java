package vse.cz.vseblog.service;

import vse.cz.vseblog.data.request.CreateEventRequestData;
import vse.cz.vseblog.data.request.SearchEventRequest;
import vse.cz.vseblog.data.response.EventResponseRST;
import vse.cz.vseblog.data.response.EventTypeResponseRST;

/**
 * Service responsible for events
 *
 * @author dusan.petren
 */
public interface EventService {

	/**
	 * Method to get all Events.
	 * Pageable
	 *
	 * @param page
	 * @param size
	 */
	EventTypeResponseRST getAllEvents(int page, int size);

	/**
	 * Method to get Event by it type.
	 * Pageable
	 *
	 * @param page
	 * @param size
	 * @param type
	 */
	EventTypeResponseRST getEventsByType(String type, int page, int size);


	/**
	 * Searches for event by name, title, content or Authors username.
	 * Pageable
	 *
	 * @param page
	 * @param size
	 * @param searchEventRequest
	 */
	EventTypeResponseRST seachEventByValue(int page, int size, SearchEventRequest searchEventRequest);


	/**
	 * Method to get Event by its id.
	 */
	EventResponseRST getEvent(long eventId);

	/**
	 * Method for creating new event.
	 *
	 * @param createEventRequestData
	 */
	EventResponseRST createEvent(CreateEventRequestData createEventRequestData);

	/**
	 * Method for editing events.
	 *
	 * If null won't update field.
	 *
	 * @param eventId
	 * @param createEventRequestData
	 */
	EventResponseRST editEvent(long eventId, CreateEventRequestData createEventRequestData);

	/**
	 * Method for editing events.
	 *
	 * If null won't update field.
	 *
	 * @param eventId
	 */
	void deleteEvent(Long eventId);

	/**
	 * Method for deleting comments by id.
	 *
	 * @param commentId
	 */
	void deleteComment(Long commentId);


}
