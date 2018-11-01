package jdev.services;


import jdev.tracker.PointDTO;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class SendService extends RouteBuilder {

    private static Logger log = LoggerFactory.getLogger(SendService.class);

    @Value("${time.interval}")
    private String timeInterval;

    @Autowired
    SaveService saveService;

    @Autowired
    PointDTO dto;

    int i = 1;

    @Override
    public void configure() throws Exception {
        from("timer://timer?period=" + timeInterval)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        log.info("CAMEL - SEND COORDINATES"+"(-"+(i++)+"-)"+" --- >>> " + dto.toJson(saveService.getQueue()));
                    }
                });
    }
}
