package com.ngatdo.WebCrawl.job;

import com.ngatdo.WebCrawl.WebCrawlApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;

@Service
public class CrawlJob {

    private final RabbitTemplate rabbitTemplate;
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final Logger logger = LoggerFactory.getLogger(CrawlJob.class);

    private static final String url = "https://vnexpress.net/microservice/home' -H 'authority: vnexpress.net' -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36' -H 'sec-fetch-dest: empty' -H 'accept: /' -H 'sec-fetch-site: same-origin' -H 'sec-fetch-mode: cors' -H 'referer: https://vnexpress.net/' -H 'accept-language: vi-VN,vi;q=0.9,en-US;q=0.8,en;q=0.7' -H 'cookie: device_env=4; sw_version=1; _ga=GA1.2.1419954761.1587225686; _gid=GA1.2.359714657.1587225686; _dc_gtm_UA-50285069-28=1; _pk_ref=%5B%22%22%2C%22%22%2C1587225687%2C%22https%3A%2F%2Fwww.google.com%2F%22%5D; _pk_cvar=%7B%228%22%3A%5B%22eatv%22%2C%2228-03-2014%22%5D%2C%229%22%3A%5B%22fosp_session%22%2C%22zhzjzm1vzqzrzkznzjzmzr1v201uzg1yzdzizmzkznzizkzrzmzjznzdznzdzizmzrzizgzmzizjzlzkzdzizmzkzrzmzgzrzgzmzmzdznzdzhznzdzhzd2f27zdzg%22%5D%2C%2210%22%3A%5B%22fosp_gender%22%2C%220%22%5D%7D; _pk_id=36a6e4336b7c91b6.1587225687.1.1587225687.1587225687.; _pk_ses=*; _dc_gtm_UA-50285069-26=1; _gat_UA-50285069-28=1; login_system=1; __gads=ID=2d75707edaabd604:T=1587225686:S=ALNI_MZZoJY6aEBMRkFe2MH9-Jnu6HnSFQ; fosp_loc=0-0-; fosp_country=; fosp_location=0; fosp_location_zone=0; fosp_isp=0; fosp_gender=3; display_cpd=9' --compressed";

    public CrawlJob(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 3600000L)//3600 seconds = 1 hour
    private void crawl(){
        logger.info("crawl from vnexpress start at: " + (new Timestamp(System.currentTimeMillis())).toString());
        ResponseEntity<String> response = restTemplate.getForEntity(url , String.class);
        if(response.getStatusCode() == HttpStatus.OK){
            logger.info("crawl successfully !!");
            String data = response.getBody();

            int pos = 0;
            while(true){
                pos = data.indexOf("[", pos);
                if(pos == -1) break;
                int pos2 = data.indexOf("}]",pos)+2;
                String s = data.substring(pos, pos2);
                if(s.length()>2)
                    rabbitTemplate.convertAndSend(WebCrawlApplication.QUEUE_NAME, s);
                pos = pos2;
            }
        }
        else {
            System.out.println("crawl error !!");
        }

    }
}
