package vse.cz.vseblog.service;

import java.util.List;
import vse.cz.vseblog.data.request.EditUserRequest;
import vse.cz.vseblog.data.request.RegisterUserRequest;
import vse.cz.vseblog.data.response.CommentResponseRST;
import vse.cz.vseblog.data.response.UserResponseRST;

/**
 *  Service responsible for managing users
 * @author dusan.petren
 */
public interface UserService {

	/**
	 * Creates new user.
	 *
	 * @param registerUser
	 */
	void createUser(RegisterUserRequest registerUser);

	/**
	 * Loads user by username.
	 *
	 * @param username
	 */
	UserResponseRST loadUser(String username);

	/**
	 * Loads all users.
	 */
	List<UserResponseRST> loadAllUsers();


	/**
	 * Edits user.
	 */
	UserResponseRST editUser(String username, EditUserRequest editUserRequest);

	/**
	 * Search for user by username, firstname, lastname or email.
	 *
	 * @param searchValue
	 */
	List<UserResponseRST> searchUsers(String searchValue);

	/**
	 * Loads Users comment.
	 *
	 * @param username
	 */
	List<CommentResponseRST> loadUsersComments(String username);

	/**
	 * Removes User by id.
	 *
	 * @param username
	 */
	void deleteUser(String username);
}
