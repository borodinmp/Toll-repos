package jdev.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
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
    public void put() throws JsonProcessingException, InterruptedException {

        String coordinates = dto.getCoordinates().get(i++).toString();
        log.info("PUT COORDINATES - >>>> " +i);
        saveService.put(coordinates);
        }










/*
        System.out.println("Добавляем значение lat = ");
        saveService.put((coordinate.getLatitude().toString);
    }

    @Scheduled(fixedDelay = 1000)
    public void info() throws InterruptedException {
        System.out.println("Scheduled ->>> take: " + saveService.getQueue());
    }*/



}

