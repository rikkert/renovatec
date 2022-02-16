package net.ripencc.compo.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Config {
    private String apiversion = "1";
    private String author;
    private String color;
    private String head;
    private String tail;
    private String version;
}
