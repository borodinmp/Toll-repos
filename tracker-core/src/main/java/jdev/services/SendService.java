package jdev.services;

import jdev.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SendService {

    @Autowired
    private SaveService saveService;

    private static final Logger log = LoggerFactory.getLogger(SendService.class);

    RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedDelay = 1000)
    private void GPSMessaging() throws InterruptedException {

        Point point;

        while (!saveService.getQueue().isEmpty()) {

            point = saveService.take();

            HttpEntity<Point> sendEntity = new HttpEntity<>(point, getHeaders());
            ResponseEntity<String> response = restTemplate.postForEntity("http://127.0.0.1:8080/coordinates", sendEntity, String.class);

            log.info(response.getBody());
        }

    }

    private static HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}
