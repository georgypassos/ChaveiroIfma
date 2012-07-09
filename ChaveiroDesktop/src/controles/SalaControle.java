package controles;

import java.util.List;

import javax.swing.JOptionPane;

import telas.TelaGerenciaSala;
import dao.SalaDAO;
import entidade.Sala;
import excecoes.SistemaException;

public class SalaControle extends Controle {

	private SalaDAO salaDao = SalaDAO.getInstance();

	private static SalaControle salaControle;
	
	private SalaControle(){ }
	
	public static SalaControle getInstance(){
		
		if(salaControle == null){
			salaControle = new SalaControle();
		}
		
		return salaControle;
	}
	
	public Sala salvar(TelaGerenciaSala tela){
		
		/**
		 * TODO Problema ao mudar satus da sala
		 * 
		 * a sala deve ter um emprestimo e cliente associado para ficar aberta.
		 * 
		 * */
		
		try {

			Sala sala = getSala(tela);
			
			validarCampos(sala);
			
			sala = salaDao.salvar(sala);
			
			utilidades.msgInformation("Sala salva com sucesso!");
			
			return sala;
			
		} catch (SistemaException e) {
			utilidades.msgWarning(e.getMessage());
		}
		
		return tela.getSala();
		
	}
	
	public void excluir(TelaGerenciaSala tela){
		
		try {
			
			Sala sala = (Sala) tela.getModelTableConsulta().getSelectedKey();

			if(utilidades.getYesNoOption("Deseja realmente excluir " + sala.getNome() + " ?") == JOptionPane.YES_OPTION){
				
				salaDao.excluir(sala.getIdsala());
				
				utilidades.msgInformation("Sala excluida com sucesso!");
			}
			
		} catch (NullPointerException e) {
			utilidades.msgError("Selecione um item para excluir");
		}
		
	}
	
	public List<Sala> consultaPorNome(String nome) throws SistemaException{
		
		List<Sala> list = salaDao.consultaPorNome(nome);
		
		if(list != null && list.size()>0){
			return list;
		}
		else{
			throw new SistemaException("Sem resultados!");
		}
		
	}
	
	private Sala getSala(TelaGerenciaSala tela) {
		
		Sala sala = tela.getSala();
		
		sala.setNome(tela.getTfNomeSala().getText());
		sala.setStatus((Integer) utilidades.getValueFromCombo(tela.getCbStatus())); 
		
		return sala;
	}

	private void validarCampos(Sala sala) throws SistemaException {

		if(sala.getNome().trim().equals(""))
			throw new SistemaException("O nome da sala e' obrigatorio");
		
	}
	
}
