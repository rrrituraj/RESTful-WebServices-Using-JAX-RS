package org.rituraj.home.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.rituraj.home.messenger.model.Message;
import org.rituraj.home.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	private MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getMessages() {
		return messageService.getAllMessages();
	}

    /**
     *
     * @param id
     * @return Response
     */
    @GET
    @Path("/{messageId}")
    public Response getMessage(@PathParam("messageId") long id) {

        if (messageService.getMessage(id)==null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(messageService.getMessage(id)).build();
        }
        return Response.status(Response.Status.FOUND)
                .entity(messageService.getMessage(id)).build();
    }

    /**
     *
     * @param message
     * @param uriInfo
     * @return Response
     * @return  the location as Headers
     * @return Message
     */
	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(message.getId());

		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();

		return Response.created(uri).entity(newMessage).build();

		//return Response.status(Response.Status.CREATED).entity(newMessage).build();
	}

    /**
     *
     * @param id
     * @param message
     * @return Response with entity object
     */
	@PUT
	@Path("/{messageId}")
	public Response updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		if (messageService.getMessage(id)==null){
		    return Response.status(Response.Status.NOT_FOUND)
                    .entity(messageService.updateMessage(message)).build();
        }
		return Response.status(Response.Status.ACCEPTED)
                .entity(messageService.updateMessage(message)).build();
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}
	


	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
}
