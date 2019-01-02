package jdev;

import jdev.services.GPSService;
import jdev.services.SaveService;
import jdev.services.SendService;
import jdev.services.KmlImport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
@PropertySource("classpath:application.properties")
public class InjectionContext {

    @Bean
    public GPSService gpsService() {
        return new GPSService();
    }

    @Bean
    public SaveService saveService() {
        return new SaveService();
    }

    @Bean
    public SendService sendService() {
        return new SendService();
    }

    @Bean
    public KmlImport pointDTO() {
        return new KmlImport();
    }

}
