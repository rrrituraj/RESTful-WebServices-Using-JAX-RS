package org.rituraj.home.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class CommentResource {

    @GET
    public String test(){
        return "new Sub Resource";
    }

    @GET
    @Path("/{commentId}")
    public String getCommentId(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId){
        return "return the commentId: "+ commentId + " and the messageId : "+ messageId;
    }
}
