package springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MixMavenController {
    /**
     * @return a string
     */
    @GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}
