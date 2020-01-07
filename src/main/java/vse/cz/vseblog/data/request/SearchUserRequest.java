package vse.cz.vseblog.data.request;

import java.io.Serializable;

/**
 * @author dusan.petren
 */
public class SearchUserRequest implements Serializable {

	private static final long serialVersionUID = -1265953626232732000L;

	private String searchValue;

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
}
