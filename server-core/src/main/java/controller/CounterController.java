package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdev.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class CounterController {

    private static final Logger log = LoggerFactory.getLogger(CounterController.class);

    RestTemplate restTemplate = new RestTemplate();

    public CounterController(){}

    public CounterController(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/coordinates", method = RequestMethod.POST)
    public String setCoords(@RequestBody Point point) throws JsonProcessingException {

        log.info(point.toJson());

        return point.toJson();

    }

    @RequestMapping(value = "/relay")
    public String relay(){
        String str = restTemplate.getForObject(
                "http://127.0.0.1:8080/coordinates", String.class);
        return str;
    }

}