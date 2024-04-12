package co.com.roman.microservice.resolveEnigmaApi.service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {
	private final RestTemplate restTemplate;

	public RestTemplateService(RestTemplate restTemplate) {
		this.restTemplate = new RestTemplate();

	}

	public ResponseEntity<String> getAll(String url) {
		return restTemplate.getForEntity(url, String.class);
	}
}