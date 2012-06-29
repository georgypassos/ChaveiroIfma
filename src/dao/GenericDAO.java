package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class GenericDAO<T> {
	/*
	 * cria um gerenciador de entidades, a partir da fábrica de gerenciadores de entidade Obs: não utilizar static, pois
	 * pode gerar problemas em relacionamentos Many-To-Many
	 */
	private EntityManager em = Persistence.createEntityManagerFactory("ChaveiroPU").createEntityManager();
	
	@SuppressWarnings("rawtypes")
	private Class classe;

	@SuppressWarnings("rawtypes")
	public GenericDAO(Class classe) {
		this.classe = classe;
	}

	public void insert(T entidade) {
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

	// Retorna todos os registros no banco de dados da tabela equivalente a entidade passada por parâmetro
	public List<T> list() {
		return em.createQuery("SELECT e FROM " + classe.getSimpleName() + " e").getResultList();
	}
	
	
	
	
}
