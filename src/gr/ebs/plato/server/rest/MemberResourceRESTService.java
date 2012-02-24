package gr.ebs.plato.server.rest;

import gr.ebs.plato.service.data.HelloService;
import gr.ebs.plato.service.model.Member;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members table.
 */
@Path("/members")
@Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
public class MemberResourceRESTService {
   @PersistenceContext
   private EntityManager em;
   
   @EJB
   private HelloService hello;
   
   @Inject
   private Logger log;

   @GET
   public List<Member> listAllMembers() {
      // Use @SupressWarnings to force IDE to ignore warnings about "genericizing" the results of
      // this query
      @SuppressWarnings("unchecked")
      // We recommend centralizing inline queries such as this one into @NamedQuery annotations on
      // the @Entity class
      // as described in the named query blueprint:
      // https://blueprints.dev.java.net/bpcatalog/ee5/persistence/namedquery.html
      final List<Member> results = em.createQuery("select m from Member m order by m.name").getResultList();
      return results;
   }
   
   @GET
   @Path("/hello")
   @Produces(MediaType.TEXT_XML)
   public String hello() {
	  log.info("HelloService!");
      return "<hello>"+hello.sayHello()+"</hello>";
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   public Member lookupMemberById(@PathParam("id") long id) {
	   Member member = em.find(Member.class, id);
      return member;
   }
}
