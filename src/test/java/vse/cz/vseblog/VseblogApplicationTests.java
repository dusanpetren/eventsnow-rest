package vse.cz.vseblog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VseblogApplicationTests {

	@Test
	void contextLoads() {
		int page = 10;
		Integer numberOfevent = 11;

		System.out.println((int) Math.ceil((numberOfevent.floatValue() / page)));
	}

}
