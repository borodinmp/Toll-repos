package jdev.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import jdev.dto.Point;
import jdev.tracker.PointDTO;
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
    PointDTO dto;

    private static Logger log = LoggerFactory.getLogger(SendService.class);

    private int i;

    @Scheduled(fixedDelay = 1000)
    public void put() throws InterruptedException {
        Point point = new Point();
        point.setCoords(dto.getCoordinates().get(i++).toString());
        log.info("PUT COORDINATES - >>>> " +i);
        saveService.put(point);
        }


}

