package com.malachi.wikimedia.producer.rest;


import com.malachi.wikimedia.producer.stream.WikimediaStreamConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wikimedia")
public class WikimediaController {
    private final WikimediaStreamConsumer wikimediaStreamConsumer;


    @GetMapping
    public ResponseEntity<String> startPublish(){
        wikimediaStreamConsumer.consumeStreamAndPublish();
        return ResponseEntity.ok().body("stream publishing started");
    }
}
