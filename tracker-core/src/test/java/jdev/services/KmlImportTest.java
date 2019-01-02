package jdev.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import jdev.dto.Point;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class KmlImportTest {

    @Test
    public void getCoordinates() {
    KmlImport kmlImport = new KmlImport();
    assertNotNull(kmlImport.getCoordinates());
    }

    @Test
    public void toJson() throws JsonProcessingException {
            Point point = new Point();
            point.setCoords("39.988894,56.024576,122.0");
            assertTrue(point.toJson().contains("39.988894,56"));
            System.out.println(point.toJson());

    }
}