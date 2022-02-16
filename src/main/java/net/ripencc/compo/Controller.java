package net.ripencc.compo;

import net.ripencc.compo.algo.Oob;
import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Hello;
import net.ripencc.compo.dto.Config;
import net.ripencc.compo.dto.Move;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

import static java.time.LocalDateTime.now;

@RestController
public class Controller {

    private static final String template = "Hello, %s!";
    private static final LocalDateTime bootTime = now();
    private final AtomicLong counter = new AtomicLong();

    Logger logger = LoggerFactory.getLogger(Controller.class);
    Oob oob;

    @Autowired
    public Controller(Oob oob) {
        this.oob = oob;
    }

    @GetMapping("/")
    public Config getConfig() {
        return Config.builder()
                .color("#3D9970")
                .author("Renovate")
                .head("bendr")
                .tail("freckled")
                .version("0.1.43-alpha")
                .build();
    }

    @PostMapping("/start")
    public void start(@RequestBody Battle battle) {
        logger.info("Starting battle: {}", battle);
    }

    @PostMapping("/move")
    public Move move(@RequestBody Battle battle) {
        var result = oob.move(battle);

        logger.info("Moving to {}", result);
        return result;
    }

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
