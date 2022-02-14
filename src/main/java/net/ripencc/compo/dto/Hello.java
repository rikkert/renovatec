package net.ripencc.compo.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class Hello {

    private final long id;
    private final String content;
    private final List<String> RESULT = new ArrayList<>(0);
    private final Duration duration;

    public ZonedDateTime getNow() {
        return ZonedDateTime.now();
    }
}