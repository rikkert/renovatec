package net.ripencc.compo;

import net.ripencc.compo.dto.Hello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

import static java.time.LocalDateTime.now;

@RestController
public class Controller {

    private static final String template = "Hello, %s!";
    private static final LocalDateTime bootTime = now();
    private final AtomicLong counter = new AtomicLong();

    Logger logger = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/greeting")
    public Hello greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        logger.info("greeting from renovate");

        return Hello.builder()
                .id(counter.incrementAndGet())
                .duration(Duration.between(bootTime, now()))
                .content(String.format(template, name))
                .build();
    }
}
