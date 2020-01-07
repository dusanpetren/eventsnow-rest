package vse.cz.vseblog.data.request;

import java.io.Serializable;

/**
 * Request data for searching in events
 *
 * @author dusan.petren
 */
public class SearchEventRequest implements Serializable {

	private static final long serialVersionUID = -3333913588885273783L;
	private String searchValue;

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
}
