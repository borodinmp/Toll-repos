package jdev.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void getCoords() {
        Point point = new Point();
        point.setCoords("39.988894,56.024576,122.0");
        assertNotNull(point.getCoords());
    }

    @Test
    public void setCoords() {
        Point point = new Point();
        point.setCoords("39.988894,56.024576,122.0");
        assertEquals(point.getCoords(), "39.988894,56.024576,122.0");
    }

    @Test
    public void toJson() throws JsonProcessingException {
        Point point = new Point();
        point.setCoords("39.988894,56.024576,122.0");
        assertTrue(point.toJson().contains("39.988894,56"));
        System.out.println(point.toJson());


    }

}