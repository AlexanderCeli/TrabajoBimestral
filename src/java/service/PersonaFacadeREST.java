/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import modelo.Persona;

/**
 *
 * @author Alexander
 */
@Stateless
@Path("modelo.persona")
public class PersonaFacadeREST extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "JoyeriaPU")
    private EntityManager em;

    public PersonaFacadeREST() {
        super(Persona.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Persona entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Persona entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Persona find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Persona> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Persona> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    public String crear(@FormParam("cedula") String cedula, @FormParam("nombre") String nombre,
            @FormParam("direccion") String direccion, @FormParam("coreo") String coreo, @FormParam("telefono") String telefono, 
            @FormParam("ciudad") String ciudad, @FormParam("edad") Integer edad,@FormParam("preferencia") Integer preferencia) {
        Persona e = new Persona(cedula,nombre, direccion, coreo, telefono, ciudad,edad, preferencia);
        super.create(e);
        return "el objeto se creo con exito";
    }
     @POST
    @Path("read")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Persona> leeraplellido(@FormParam("apellido") String apellido) {
        TypedQuery consulta = getEntityManager().createQuery("SELECT p FROM Personas p WHERE p.apellido = :apellido", Persona.class);
        consulta.setParameter("apellido", apellido);
        return consulta.getResultList();
    }
    @POST
    @Path("editar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String editar(@FormParam("cedula") String cedula, @FormParam("nombre") String nombre,
            @FormParam("direccion") String direccion, @FormParam("coreo") String coreo, @FormParam("telefono") String telefono, 
            @FormParam("ciudad") String ciudad, @FormParam("edad") Integer edad,@FormParam("preferencia") Integer preferencia) {
        Persona e = super.find(cedula);

        e.setNombre(nombre);
        e.setDireccion(direccion);
        e.setCoreo(coreo);
        e.setTelefono(telefono);
        e.setCiudad(ciudad);
        e.setEdad(edad);
        e.setPreferencia(preferencia);
        super.create(e);
        return "el objeto se edito con exito";
    }
 @Path("eliminar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String eliminar(@FormParam("cedula") Integer cedula) {
        Persona e = super.find(cedula);
        
        if(e == null){
            return ("no se econtro objeto");
        }else{
            super.remove(e);
            return ("se elimino");
        }
        
    } 
     @POST
    @Path("clienteJoye")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Persona> joyeria(@FormParam("idjoyeria") Integer preferencia ){
        TypedQuery consulta = getEntityManager().createQuery("SELECT p FROM Persona p WHERE p.preferencia= :preferencia", Persona.class);
        consulta.setParameter("idjoyeria", preferencia);
        return consulta.getResultList();
    }
    @POST
    @Path("edades")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Persona> edades(@FormParam("edad") Integer edad ){
        TypedQuery consulta = getEntityManager().createQuery("SELECT p FROM Persona p WHERE p.edad >= :edad", Persona.class);
        consulta.setParameter("edad", edad);
        return consulta.getResultList();
    }
    
    
    }