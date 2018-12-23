package jdev.services;

import jdev.dto.Point;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Service
public class SaveService {

    private BlockingDeque<Point> queue = new LinkedBlockingDeque<>(100);

    public void put(Point point) throws InterruptedException {
        queue.put(point);
    }

    public Point take() throws InterruptedException {
        return queue.take();
    }

    BlockingDeque<Point> getQueue() {
        return queue;
    }

}
