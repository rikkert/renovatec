package net.ripencc.compo;

import net.ripencc.compo.algo.Strategy;
import net.ripencc.compo.dto.Battle;
import net.ripencc.compo.dto.Config;
import net.ripencc.compo.dto.Move;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    Logger logger = LoggerFactory.getLogger(Controller.class);
    Strategy strategy;
    Gokcem gokcem;

    @Autowired
    public Controller(Strategy strategy,Gokcem gokcem) {
        this.strategy = strategy;
        this.gokcem = gokcem;
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
//        var result = gokcem.move(battle);
        var result = strategy.determineNextMove(battle);

        logger.info("Moving to {}", result);
        return result;
    }
}
