package com.malachi.wikimedia.producer.stream;


import com.malachi.wikimedia.producer.producer.WikimediaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class WikimediaStreamConsumer {
    private final WebClient webClient;
    private final WikimediaProducer producer;

    public WikimediaStreamConsumer(WebClient.Builder webClientBuilder, WikimediaProducer producer) {
        this.webClient = webClientBuilder
                .baseUrl("https://stream.wikimedia.org")
                .build();
        this.producer = producer;
    }

    public void consumeStreamAndPublish(){
        webClient
                .get()
                .uri("/v2/stream/recentchange")
                //to extract only the body of the response without the headers and status
                .retrieve()
                .bodyToFlux(String.class)
                //the consumer to invoke on each value(onNext Signal)
                .subscribe(producer::sendMessage);


    }
}
