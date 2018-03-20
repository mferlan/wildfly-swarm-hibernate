package hr.mferlan.example;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.inject.Inject;

import org.hibernate.Session;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.wildfly.swarm.arquillian.DefaultDeployment;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Integration test for using the custom Hibernate ORM version on WildFly Swarm.
 */
@RunWith(Arquillian.class)
@DefaultDeployment
public class CustomHibernateVersionTest {

	/** The entity manager. */
	@Inject
	private PersistenceHelper helper;


	@Test
	@Transactional(value=TransactionMode.ROLLBACK)
	public void shouldUseHibernateOrm53() {
		Session session = helper.getEntityManager().unwrap( Session.class );

		MyEntity entity1 = new MyEntity();
		entity1.id = 1L;
		entity1.description = "Some MyEntity";
		session.persist( entity1 );

		session.flush();
		session.clear();
		
		MyEntity loaded = session.find( MyEntity.class, 1L );

		assertThat( loaded.description, equalTo( "Some MyEntity" ) );
	}
}