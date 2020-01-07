package vse.cz.vseblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import vse.cz.vseblog.data.request.EditUserRequest;
import vse.cz.vseblog.data.request.JwtRequest;
import vse.cz.vseblog.data.request.RegisterUserRequest;
import vse.cz.vseblog.data.request.SearchUserRequest;
import vse.cz.vseblog.data.response.CommentResponseRST;
import vse.cz.vseblog.data.response.JwtResponse;
import vse.cz.vseblog.data.response.UserResponseRST;
import vse.cz.vseblog.service.CommentService;
import vse.cz.vseblog.service.UserService;
import vse.cz.vseblog.util.JwtTokenUtil;

/**
 * Api for manipulation with User
 *
 * @author dusan.petren
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);


	@Autowired
	private UserService userServiceImpl;
	@Autowired
	private CommentService commentServiceImpl;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserDetailsService userDetailsServiceImpl;


	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		logger.info("Authenticating User with username {}", authenticationRequest.getUsername());

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsServiceImpl
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getUserFromToken(@RequestHeader HttpHeaders headers) throws Exception {

		final String username = jwtTokenUtil.getUsernameFromToken(headers.get(HttpHeaders.AUTHORIZATION).get(0));
		final UserResponseRST userResponse = userServiceImpl.loadUser(username);

		return ResponseEntity.ok(userResponse);
	}


	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
	)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<?> createUser(@RequestBody RegisterUserRequest registerUserRequest) {
		logger.info("Creating new User for data: {}", registerUserRequest);

		this.userServiceImpl.createUser(registerUserRequest);
		return new ResponseEntity<>("User created", HttpStatus.CREATED);
	}

	@RequestMapping(value = "/secured/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<UserResponseRST>> getUsers() {
		logger.info("Getting all Users");

		return new ResponseEntity<>(userServiceImpl.loadAllUsers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/secured/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<UserResponseRST>> searchForUser(@RequestBody SearchUserRequest searchUserRequest) {
		logger.info("Getting all Users");

		return new ResponseEntity<>(userServiceImpl.searchUsers(searchUserRequest.getSearchValue()), HttpStatus.OK);
	}

	@RequestMapping(value = "/secured/{username}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<UserResponseRST> loadUser(@PathVariable("username") String username) {
		logger.info("Getting User for username: {}", username);

		return new ResponseEntity<>(userServiceImpl.loadUser(username), HttpStatus.OK);
	}

	@RequestMapping(value = "/secured/{username}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public ResponseEntity<UserResponseRST> editUser(@PathVariable("username") String username, @RequestBody EditUserRequest editUserRequest) {
		logger.info("Editing User for username: {} for data {}", username, editUserRequest);

		return new ResponseEntity<>(this.userServiceImpl.editUser(username, editUserRequest), HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/secured/{username}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<String> deleteUser(@PathVariable("username") String username) {
		logger.info("Deleting User for username: {}", username);
		userServiceImpl.deleteUser(username);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/secured/{username}/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<CommentResponseRST>> getUsersComments(@PathVariable(value = "username") String username) {
		logger.info("Getting all Users comments");

		return new ResponseEntity<>(userServiceImpl.loadUsersComments(username), HttpStatus.OK);
	}

	@RequestMapping(value = "/secured/comment/{commentId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<?> deleteComment(@PathVariable(value = "commentId") Long commentId) {
		logger.info("Deleting comments");

		commentServiceImpl.deleteComment(commentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
