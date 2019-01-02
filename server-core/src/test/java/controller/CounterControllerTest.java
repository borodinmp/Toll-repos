package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdev.dto.Point;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CounterControllerTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    CounterController counterController;

    @Test
    public void setCoords() throws JsonProcessingException {

        String result;

        Point point = new Point();
        point.setCoords("39.988894,56.024576,122.0");

        CounterController controller = new CounterController();
        result = controller.setCoords(point);

        assertEquals(point.toJson(), result);

    }

    @Test
    public void GPSMessaging() {
        when(restTemplate.getForObject("http://127.0.0.1:8080/coordinates", String.class)).thenReturn(new String());
        String result = counterController.relay();
        assertNotNull(result);
    }

}