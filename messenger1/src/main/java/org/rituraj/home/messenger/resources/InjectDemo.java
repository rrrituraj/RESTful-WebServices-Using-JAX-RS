package org.rituraj.home.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("/injectdemo")
public class InjectDemo {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getInjectDemo(){
        return "Got Inject Demo";
    }
}
