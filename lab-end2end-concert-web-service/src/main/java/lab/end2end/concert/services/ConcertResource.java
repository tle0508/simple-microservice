package lab.end2end.concert.services;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


import lab.end2end.concert.domain.Concert;
import lab.end2end.concert.domain.Genre;
import lab.end2end.concert.domain.Performer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to implement a simple REST Web service for managing Concerts.
 * <p>
 * ConcertResource implements a WEB service with the following interface:
 * - GET    <base-uri>/concerts/{id}
 * Retrieves a Concert based on its unique id. The HTTP response
 * message has a status code of either 200 or 404, depending on
 * whether the specified Concert is found.
 * <p>
 * - POST   <base-uri>/concerts
 * Creates a new Concert. The HTTP post message contains a
 * representation of the Concert to be created. The HTTP Response
 * message returns a Location header with the URI of the new Concert
 * and a status code of 201.
 * <p>
 * - PUT    <base-uri>/concerts
 * Updates an existing Concert. The HTTP request message contains a
 * representation of the Concert to be updated. The HTTP response
 * message returns a status code of 204 where successful, or 404
 * where the specified Concert is unknown to the Web service.
 * <p>
 * - DELETE <base-uri>/concerts/{id}
 * Deletes a concert whose ID is specified. The HTTP response status
 * code is either 204 or 404, depending on whether the Concert exists.
 * <p>
 * - DELETE <base-uri>/concerts
 * Deletes all Concerts, returning a status code of 204.
 */
@Path("/concerts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConcertResource {

    private static Logger LOGGER = LoggerFactory.getLogger(ConcertResource.class);

    @GET
    @Path("{id}")
    public Response retrieveConcert(@PathParam("id") long id) {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        try {

            // Start a transaction for persisting the audit data.
            em.getTransaction().begin();

            // Or just the load object by ID.
            Concert concert = em.find(Concert.class, id);
            em.getTransaction().commit();

            if (concert == null) {
                // Return a HTTP 404 response if the specified Concert isn't found.
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }

            ResponseBuilder builder = Response.ok(concert);
            return builder.build();

            // An alternative to EntityManager#find() is to run a query:
            // TypedQuery<Concert> concertQuery = em.createQuery("select c from Concert c where c.id = :id", Concert.class)
            //		.setParameter("id", id);
            // Concert concert = concertQuery.getSingleResult();
        } finally {
            em.close();
        }
    }

    @POST
    public Response createConcert(Concert concert) {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(concert);
            em.getTransaction().commit();
           
            ResponseBuilder builder = Response.created(URI.create("/concerts/" + concert.getId()));
            return builder.build();
        } finally {
            em.close();
        }
    }

    @PUT
    public Response updateConcert(Concert concert) {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(concert);
            em.getTransaction().commit();

            ResponseBuilder builder = Response.status(Response.Status.NO_CONTENT);
            return builder.build();
        } finally {
            em.close();
        }
    }
    
  
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        try {
            em.getTransaction().begin();
            Concert concert = em.find(Concert.class, id);

            if (concert == null) {
                // Return a HTTP 404 response if the specified Concert isn't found.
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }

            em.remove(concert);
            em.getTransaction().commit();

            ResponseBuilder builder = Response.status(Response.Status.NO_CONTENT);
            return builder.build();
        } finally {
            em.close();
        }
    }


    @DELETE
    public Response deleteAllConcerts() {
        EntityManager em = PersistenceManager.instance().createEntityManager();
        try {
            em.getTransaction().begin();

            TypedQuery<Concert> concertQuery = em.createQuery("select c from Concert c", Concert.class);
            List<Concert> concerts = concertQuery.getResultList();

            for (Concert c : concerts) {
                em.remove(c);
            }

            em.getTransaction().commit();

            ResponseBuilder builder = Response.status(Response.Status.NO_CONTENT);
            return builder.build();

            // An alternative to loading each Concert and deleting it is to run
            // queries as follows. Note that "delete from Concert" should be run
            // first because of the foreign key constraint in the Performer table.
            // Note too that running "delete from Concert" doesn't cause the
            // associated Performers to be deleted. For cascading deletes, the
            // Concert must be loaded and deleted.
            //
            // TypedQuery<Long> query = em.createQuery("select c.performer.id from Concert c", Long.class);
            // List<Long> performerIds = query.getResultList();
            //
            // em.createQuery("delete from Concert").executeUpdate();
            // em.createQuery("delete from Performer p where p.id in :ids")
            // .setParameter("ids", performerIds)
            // .executeUpdate();
        } finally {
            em.close();
        }
    }
}
