package ressourcesrest;

import entities.Etudiant;
import entities.Option;
import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("etudiants")
public class restEtudiant {
    public static EtudiantBusiness et = new EtudiantBusiness();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AjoutEtudiant(Etudiant etudiant){
        if (et.addEtudiant(etudiant)){
            return Response.status(200).build();
        }else{
            return Response.status(404).build();
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Etudiant> l = new ArrayList<Etudiant>();
        l=et.getAllEtudiants();
        if (l.isEmpty()){
            return Response.status(404).build();
        }else{
            return Response.status(200).entity(l).build();
        }
    }
    @GET
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEt(@PathParam("code") String id){
        Etudiant e = et.getEtudiantByIdentifiant(id);
        if(e!=null){
            return Response.status(200).entity(e).build();
        }else{
            return Response.status(404).build();
        }
    }
    @DELETE
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteEt(@PathParam("code") String id){
        if(et.deleteEtudiant(id)){
            return Response.status(204).build();
        }else{
            return Response.status(404).build();
        }
    }
    @PUT
    @Path("{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOp(@PathParam("code") String id,Etudiant e ){
        if(et.updateEtudiant(id,e)){
            return Response.status(200).build();
        }else{
            return Response.status(404).build();
        }
    }
    @GET
    @Path("option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllOptions(@QueryParam("codeOption") int optionCode ){
        List<Etudiant> l = new ArrayList<Etudiant>();
        Option achercher = new Option(optionCode);
        l=et.getEtudiantsByOption(achercher);
        if (l.isEmpty()){
            return Response.status(404).build();
        }else{
            return Response.status(200).entity(l).build();
        }

    }

}
