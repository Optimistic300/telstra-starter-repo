package au.com.telstra.simcardactivator.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SimActivationService {

    public boolean forwardToActuator(String iccid) {
        String url = "http://localhost:8444/actuate";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("iccid", iccid);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Object success = response.getBody().get("success");
                return Boolean.TRUE.equals(success);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        return false;
    }
}