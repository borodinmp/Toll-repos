package jdev.services;

import jdev.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SendService {



    @Autowired
    private SaveService saveService;


    private static final Logger log = LoggerFactory.getLogger(SendService.class);

    @Scheduled(fixedDelay = 2000)
    private void GPSMessaging() throws InterruptedException {

        RestTemplate restTemplate = new RestTemplate();

        String str;
        while (true) {
            // взятие точки из очереди
            str = saveService.getQueue();

            HttpEntity<String> sendEntity = new HttpEntity<>(str, getHeaders());
            ResponseEntity<String> response = restTemplate.postForEntity("http://127.0.0.1:8080/coordinates", sendEntity, String.class);

            // вывод ответа сервера
            log.info(response.getBody());
        }

    }

    // формирование заголовка при отправке данных методом POST
    private static HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}

/*    private static Logger log = LoggerFactory.getLogger(SendService.class);

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
                .setHeader(Exchange.HTTP_METHOD,simple("POST"))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getOut().setBody(dto.toJson(i + ">> " + saveService.getQueue() + " / "));
                        log.info("CAMEL - SEND COORDINATES"+"(-"+(i++)+"-)"+" --- >>> " + dto.toJson(saveService.getQueue()));
                    }
                })
                .to("file:C:/Users/Admin/Desktop?fileName=logs.txt&fileExist=Append")
                .to("http://localhost:8080/greeting");

    }
}*/
