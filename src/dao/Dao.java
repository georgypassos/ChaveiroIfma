package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class Dao {
	
	protected static EntityManagerFactory emf;
	protected static EntityManager em;
	protected static final String UNIT_NAME = "ChaveiroPU";

	public Dao() {
		emf = Persistence.createEntityManagerFactory(UNIT_NAME);
		em = emf.createEntityManager();
	}

	public void close() {
		em.close();
		emf.close();
	}
	
}
