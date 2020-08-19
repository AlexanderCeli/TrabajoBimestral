/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import modelo.Joyeria;
import modelo.Persona;

/**
 *
 * @author Alexander
 */
@Stateless
@Path("modelo.joyeria")
public class JoyeriaFacadeREST extends AbstractFacade<Joyeria> {

    @PersistenceContext(unitName = "JoyeriaPU")
    private EntityManager em;

    public JoyeriaFacadeREST() {
        super(Joyeria.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Joyeria entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Joyeria entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Joyeria find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Joyeria> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Joyeria> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
       
    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String crear(@FormParam("idjoyeria") Integer idjoyeria, @FormParam("nombre") String nombre, @FormParam("ubicacion;") String ubicacion,
            @FormParam("direccion") String direccion,@FormParam("ciudad") String ciudad,
            @FormParam("dimenciones")Integer dimenciones, @FormParam("numeroEmergencia") String numeroEmergencia 
            ) {
        Joyeria e = new Joyeria(idjoyeria, nombre,ubicacion,direccion,ciudad, dimenciones,numeroEmergencia);
        super.create(e);
        return "el objeto se creo con exito";
    }
     @POST
    @Path("read")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Joyeria> leerJoyeria(@FormParam("idJoyeria") Integer idJoyeria) {
        TypedQuery consulta = getEntityManager().createQuery("SELECT j FROM Joyeria j WHERE j.idjoyeria = :idjoyeria", Persona.class);
        consulta.setParameter("idJoyeria",idJoyeria);
        return consulta.getResultList();
    }
    @POST
    @Path("editar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String editar(@FormParam("idjoyeria") Integer idjoyeria, @FormParam("nombre") String nombre, @FormParam("ubicacion;") String ubicacion,
            @FormParam("direccion") String direccion,@FormParam("ciudad") String ciudad,
            @FormParam("dimenciones")Integer dimenciones, @FormParam("numeroEmergencia") String numeroEmergencia 
            ) {
        Joyeria e = super.find(idjoyeria);
        e.setIdjoyeria(idjoyeria);
        e.setNombre(nombre);
        e.setUbicacion(ubicacion);
        e.setDireccion(direccion);
        e.setCiudad(ciudad);
        e.setDimenciones(dimenciones);
        e.setNumeroEmergencia(numeroEmergencia);
        super.create(e);
        return "el objeto se edito con exito";
    }
 @Path("eliminar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String eliminar(@FormParam("idJoyeria") Integer idJoyeria) {
        Joyeria e = super.find(idJoyeria);
        
        if(e == null){
            return ("no se econtro objeto");
        }else{
            super.remove(e);
            return ("se elimino");
        }
        
    }
    @POST
    @Path("ciuades")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Joyeria> ciud(@FormParam("ciudad") String ciudad ){
        TypedQuery consulta = getEntityManager().createQuery("SELECT j FROM Joyeria j WHERE j.ciudad = :ciudad", Joyeria.class);
        consulta.setParameter("ciudad", ciudad);
        return consulta.getResultList();
    }
    @POST
    @Path("readin")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Joyeria> Ciuda(@FormParam("ciudad") String ciudad ) {
        TypedQuery consulta = getEntityManager().createQuery("SELECT j FROM Joyeria j WHERE j.ciudad = :ciudad", Joyeria.class);
        consulta.setParameter("ciudad", ciudad);
        return consulta.getResultList();
    }
}
