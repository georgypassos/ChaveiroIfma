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
    
    public void inserir(Sala sala){
        this.merge(sala);
        System.out.println("Sala salva com sucesso!");
    }
            
    public void mudarStatus(int id, int status){
        Sala sala = (Sala) this.get(id);
        sala.setStatus(status);
        this.merge(sala);
    }
    
    public List<Sala> consultar(){
    	
    	String sql = "FROM Sala s";
    	
    	return search(sql, null);
    }

	public List<Sala> consultaPorNome(String nome){
		
		String sql = "FROM Sala s WHERE s.codigo LIKE :p0 ORDER BY s.codigo ASC";
		
		return search(sql, "%"+nome+"%");
	}
	
}
