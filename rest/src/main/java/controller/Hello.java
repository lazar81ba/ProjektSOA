package controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "rest.Hello there!";
    }
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> rest.Hello there!" + "</hello>";
    }
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHTMLHello() {
        return "<html> " + "<title>" + "rest.Hello Jersey" + "</title>"
                + "<body><h1>" + "rest.Hello there!" + "</body></h1>" + "</html> ";
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    @Path("/xml")
    public String sayXMLHello2() {
        return sayXMLHello();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/message/xml")
    public Message sendMessage() {
        return new Message("title", "body");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/message/json")
    public Message sendJSONMessage() {
        return new Message("title", "body");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/to/{name}")
    public String sayHelloTo(@PathParam("name") String name) {
        return "Hello there " + name + "!";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/to")
    public String sayHelloTo2(@QueryParam("name") String name) {
        return "Hello there " + name + "!";
    }
}