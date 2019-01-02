package jdev.services;

import jdev.dto.Point;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaveServiceTest {

    @Test
    public void put() throws InterruptedException {
        SaveService service = new SaveService();
        Point point = new Point();
        service.put(point);
        assertNotNull(point);
    }

    @Test
    public void take() throws InterruptedException {
        SaveService service = new SaveService();
        Point point = new Point();
        service.put(point);
        assertNotNull(service.take());
    }

    @Test
    public void getQueue() {
        SaveService service = new SaveService();
        assertNotNull(service.getQueue());
    }
}