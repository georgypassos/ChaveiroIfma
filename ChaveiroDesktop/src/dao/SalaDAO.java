package dao;

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
        this.insert(sala);
        System.out.println("Sala salva com sucesso!");
    }
            
    public void mudarStatus(int id){
        Sala sala = (Sala) this.get(id);
        sala.setStatus(!sala.getStatus());
        this.update(sala);
    }
    
    
    public static void main(String[] args) {
        SalaDAO s = SalaDAO.getInstance();
        Sala sala = new Sala();
        sala.setCodigo("sala8");
        sala.setStatus(true);
        s.inserir(sala);
        s.mudarStatus(4);
    }
    
}
