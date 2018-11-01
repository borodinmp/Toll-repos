package jdev.tracker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class PointDTO {

    public List getCoordinates() {
        final Kml kml = Kml.unmarshal(new File("C:\\Users\\Admin\\IdeaProjects\\Toll\\tracker-core\\track.kml"));
        final Placemark placemark = (Placemark) kml.getFeature();
        Point point = (Point) placemark.getGeometry();
        List<Coordinate> coordinates = point.getCoordinates();
        return coordinates;
    }

    public String toJson(String str) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(str);
    }

}