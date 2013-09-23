package com.langtianlang.chessie.service.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

import javax.validation.constraints.NotNull;

public class ChessieServiceConfiguration extends Configuration {

    @NotNull
    @JsonProperty
    private String storeName;

    public String getStoreName() {
        return storeName;
    }

}
