package vse.cz.vseblog.data.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author dusan.petren
 */
public class EventTypeResponseRST implements Serializable {

	private static final long serialVersionUID = 428498514535377536L;

	private List<EventResponseRST> eventsList;
	private Integer availablePages;

	public List<EventResponseRST> getEventsList() {
		return eventsList;
	}

	public void setEventsList(List<EventResponseRST> eventsList) {
		this.eventsList = eventsList;
	}

	public Integer getAvailablePages() {
		return availablePages;
	}

	public void setAvailablePages(Integer availablePages) {
		this.availablePages = availablePages;
	}
}
