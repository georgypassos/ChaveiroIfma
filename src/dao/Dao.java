package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@SuppressWarnings("rawtypes")
public abstract class Dao<T> {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static final String UNIT_NAME = "ChaveiroPU";
	
	private Class classe;
	
	protected Dao(Class classe) {
		
		this.classe = classe;
		
		if(em == null){
			emf = Persistence.createEntityManagerFactory(UNIT_NAME);
			em = emf.createEntityManager();
		}
		
	}
	
	protected void insert(T entidade){
		em.getTransaction().begin();
        em.persist(entidade);
        em.getTransaction().commit();
	}
	
	public void update(T entidade) {
		em.getTransaction().begin();
		em.merge(entidade);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public T get(int id) {
		return (T) em.find(classe, id);
	}

	public void remove(int id) {
		T entidade = get(id);
		if (entidade != null) {
			em.getTransaction().begin();
			em.remove(entidade);
			em.getTransaction().commit();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> search(String sql, Object... parameters) {
		
		Query q = em.createQuery(sql);
		
		int i = 0;
		for(Object p : parameters){
			q.setParameter("p"+i, p);
			
			i++;
		}
		
		return q.getResultList();
	}

	public void close() {
		em.close();
		emf.close();
	}
	
}
