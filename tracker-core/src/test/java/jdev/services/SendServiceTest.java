package jdev.services;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SendServiceTest {

    @Mock
    private RestTemplate restTemplate = new RestTemplate();

    @InjectMocks
    GPSService gpsService = new GPSService();

    @InjectMocks
    SaveService saveService = new SaveService();

    @InjectMocks
    private SendService sendService = new SendService();

    @InjectMocks
    private KmlImport kmlImport;

    @Test
    public void fileTest() {
        assertNotNull(kmlImport.getCoordinates());
    }

    @Test
    public void relayIntegration() throws Exception {
        Point point = new Point();
        point.setCoords("11");
        when(restTemplate.postForEntity(eq("http://127.0.0.1:8080/coordinates"), any(HttpEntity.class), eq(Point.class))).thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        saveService.put(point);
        sendService.GPSMessaging();
        assertEquals(1, saveService.getQueue().size());
    }

}