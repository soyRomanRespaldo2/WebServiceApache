package co.com.roman.microservice.resolveEnigmaApi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrquestadorService {
	
	public String fullAnswer() {
		RestTemplate restTemplate1 = new RestTemplate();
		RestTemplate restTemplate2 = new RestTemplate();
		RestTemplate restTemplate3 = new RestTemplate();
		
		String resourseUrl1 = ("http://localhost:8081/v1/getOneEnigma/getNewStep");
		String resourseUrl2 = ("http://localhost:8082/v1/getOneEnigma/getNewStep");
		String resourseUrl3 = ("http://localhost:8083/v1/getOneEnigma/getNewStep");
		
		ResponseEntity<String> response1 = restTemplate1.getForEntity(resourseUrl1, String.class);
		ResponseEntity<String> response2 = restTemplate2.getForEntity(resourseUrl2, String.class);
		ResponseEntity<String> response3 = restTemplate3.getForEntity(resourseUrl3, String.class);
		
		String body1 = response1.getBody();
		String body2 = response2.getBody();
		String body3 = response3.getBody();
		
		String finalResponse = body1.concat(body2).concat(body3);
		
		return finalResponse;
	}
	
}