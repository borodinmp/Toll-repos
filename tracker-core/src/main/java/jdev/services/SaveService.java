package jdev.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Service
public class SaveService {

    private BlockingDeque<String> queue = new LinkedBlockingDeque<>(100);

    public void put(String str) throws InterruptedException {
        queue.put(str);
    }

    public String getQueue() throws InterruptedException {
        return queue.take();
    }

}
