package vse.cz.vseblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import vse.cz.vseblog.entity.UserDetail;
import vse.cz.vseblog.entity.UserEntity;
import vse.cz.vseblog.repository.UserRepository;

/**
 * @author dusan.petren
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserEntity> optionalUser = userRepository.findByUsername(username);

		optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username not found"));

		return optionalUser.map(UserDetail::new).get();
	}

}
