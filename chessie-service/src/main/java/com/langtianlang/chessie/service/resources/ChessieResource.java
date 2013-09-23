package com.langtianlang.chessie.service.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/v1/chess")
public final class ChessieResource {

    public ChessieResource() {
    }

    @GET
    public Response get() {
        return Response.ok("hello").build();
    }

}
