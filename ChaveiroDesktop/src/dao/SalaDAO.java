package dao;

import java.util.List;

import entidade.Sala;

public class SalaDAO extends Dao<Sala> {
    
    private static final SalaDAO salaDAO = new SalaDAO();
    
    private SalaDAO(){
        super(Sala.class);
    }
    
    public static SalaDAO getInstance(){
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
    
    public static void main(String[] args) {
        SalaDAO s = SalaDAO.getInstance();
        Sala sala = new Sala();
        sala.setCodigo("sala8");
        sala.setStatus(Sala.STATUS_ABERTA);
        s.inserir(sala);
        
        System.out.println("-->> "+s.consultar().get(0).getStatus());
        
    }
    
}
