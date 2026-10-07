package ressourcesrest;

import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
@Path("options")
public class restOption {
    public static OptionBusiness optB = new OptionBusiness();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOptions(@QueryParam("domaine") String domaine ){
        List<Option> l = new ArrayList<Option>();
        if (domaine==null){
            l=optB.getListeOptions();
        }else {
            l=optB.getOptionsByDomaine(domaine);
        }
        if(l.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.status(200).entity(l).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AjoutOption(Option op){
        if (optB.addOption(op)){
            return Response.status(200).build();
        }else{
            return Response.status(404).build();
        }
    }
    @DELETE
    @Path("{code}")
    public Response deleteOp(@PathParam("code") int id){
        if (optB.deleteOption(id)){
            return Response.status(204).build();
        }else{
            return Response.status(404).entity("erreur de suppresion").build();
        }
    }
    @PUT
    @Path("{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOp(@PathParam("code") int id,Option option ){
        if(optB.updateOption(id, option)){
            return Response.status(200).build();
        }else{
            return Response.status(404).build();
        }
    }
    @GET
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOp(@PathParam("code") int id){
        Option o = optB.getOptionByCode(id);
        if(o!=null){
            return Response.status(200).entity(o).build();
        }else{
            return Response.status(404).build();
        }
    }

}
