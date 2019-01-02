package jdev.services;

import jdev.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class GPSService {

    @Autowired
    SaveService saveService;

    @Autowired
    KmlImport kml;

    private static Logger log = LoggerFactory.getLogger(SendService.class);

    private int i;

    @Scheduled(fixedDelay = 1000)
    public void put() throws InterruptedException {
        Point point = new Point();
        point.setCoords(kml.getCoordinates().get(i++).toString());
        log.info("PUT COORDINATES - >>>> " +i);
        saveService.put(point);
        }


}

