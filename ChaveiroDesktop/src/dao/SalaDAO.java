package dao;

import java.util.List;

import entidade.Sala;

public class SalaDAO extends Dao<Sala> {
    
    private static SalaDAO salaDAO;
    
    private SalaDAO(){
        super(Sala.class);
    }
    
    public static SalaDAO getInstance(){
    	if(salaDAO == null){
    		salaDAO = new SalaDAO();
    	}
    	
        return salaDAO;
    }
    
    public Sala salvar(Sala sala) {

		return merge(sala);
	}

	public void excluir(int id) {
		
		remove(id);
	}

	public Sala getSala(int id) {

		return get(id);
	}

    public Sala mudarStatus(int id, int status){
        Sala sala = (Sala) this.get(id);
        sala.setStatus(status);
        return merge(sala);
    }
    
    public List<Sala> consultar(){
    	
    	String sql = "FROM Sala s";
    	
    	return search(sql, null);
    }

	public List<Sala> consultaPorNome(String nome){
		
		String sql = "FROM Sala s WHERE s.nome LIKE :p0 ORDER BY s.nome ASC";
		
		return search(sql, "%"+nome+"%");
	}
	
}
