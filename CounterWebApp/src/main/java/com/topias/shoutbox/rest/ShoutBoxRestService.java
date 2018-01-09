package com.topias.shoutbox.rest;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.HtmlUtils;

import com.google.gson.Gson;
import com.topias.shoutbox.model.Shout;
import com.topias.shoutbox.service.ShoutService;



public class ShoutBoxRestService {

    @Autowired
    private ShoutService shoutService;

    @Produces( "application/json" )
    @Consumes( "application/json" )
    @POST
    @Path( "/add" )
    public Response addShout( @HeaderParam( "nickname" ) String nickname, @HeaderParam( "content" ) String content ) {
        System.out.println( "Saving started........." );
        String escaped = escape( content );
        String escapedNickname = escape( nickname );
        shoutService.addShout( escapedNickname, escaped );
        return Response.ok( "{ \"result\": \"" + escapedNickname + "\"}", MediaType.APPLICATION_JSON ).build();
    }

    @Produces( "application/json" )
    @Consumes( "application/json" )
    @GET
    @Path( "/load" )
    public Response loadShout() {
        System.out.println( "Loading started........." );
        List<Shout> shouts = shoutService.loadShouts();

            Gson gson = new Gson();

            String json = gson.toJson( shouts );

            return Response.ok( json, MediaType.APPLICATION_JSON ).build();



    }

    public String escape( String contents ) {
        String escaped = HtmlUtils.htmlEscape( contents );
        return escaped;
    }


}
