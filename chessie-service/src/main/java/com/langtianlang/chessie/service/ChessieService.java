package com.langtianlang.chessie.service;

import com.yammer.dropwizard.config.Bootstrap;
import com.langtianlang.chessie.service.config.ChessieServiceConfiguration;
import com.langtianlang.chessie.service.resources.ChessieResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Environment;

public class ChessieService extends Service<ChessieServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new ChessieService().run(args);
    }

    private ChessieService() {
        super();
    }

    @Override
    public void initialize(Bootstrap<ChessieServiceConfiguration> bootstrap) {
        bootstrap.setName("chessie");
    }

    @Override
    public void run(final ChessieServiceConfiguration config, Environment environment) throws Exception {
        ChessieResource chessieResource = new ChessieResource();
        environment.addResource(chessieResource);
    }
}
