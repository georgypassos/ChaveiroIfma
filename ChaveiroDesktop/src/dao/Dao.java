package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@SuppressWarnings("rawtypes")
public abstract class Dao<T> {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private static final String UNIT_NAME = "ChaveiroPU";

	private Class classe;

	protected Dao(Class classe) {

		this.classe = classe;

		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(UNIT_NAME);
		}

	}

//	protected void insert(T entidade) {
//		em = emf.createEntityManager();
//		
//		em.getTransaction().begin();
//		em.persist(entidade);
//		em.getTransaction().commit();
//	}

	public T merge(T entidade) {
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		entidade = em.merge(entidade);
		em.getTransaction().commit();
		
		return entidade;
	}

	@SuppressWarnings("unchecked")
	public T get(int id) {
		em = emf.createEntityManager();
		
		return (T) em.find(classe, id);
	}

	public void remove(int id) {
		em = emf.createEntityManager();
		
		T entidade = get(id);
		if (entidade != null) {
			em.getTransaction().begin();
			em.remove(entidade);
			em.getTransaction().commit();
		}
	}
	
	public void remove(T entidade){
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.remove(entidade);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<T> search(String sql, Object... parameters) {

		em = emf.createEntityManager();
		
		Query q = em.createQuery(sql);

		int i = 0;

		if (parameters != null) {
			for (Object p : parameters) {
				q.setParameter("p" + i, p);

				i++;
			}
		}
		
		List<T> list = q.getResultList();
		
		return list;
	}

}
