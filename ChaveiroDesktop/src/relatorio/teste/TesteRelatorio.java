package relatorio.teste;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entidade.Cliente;
import entidade.Emprestimo;
import entidade.Sala;

public class TesteRelatorio {
	
	public static List<Emprestimo> emprestimosCliente(){
		
		List<Emprestimo> list = new ArrayList<Emprestimo>();
		
		Cliente c1 = new Cliente();
		c1.setNome("Georgy Cristino");
		
		Cliente c2 = new Cliente();
		c2.setNome("Leonardo Frazão");
		
		Sala s = new Sala();
		s.setNome("Laboratorio 23");
		
		for(int i=0; i<100; i++){
			Emprestimo e = new Emprestimo();
			e.setClienteEntrega(c1);
			e.setClienteRetirada(c2);
			e.setDataEntrega(new Date());
			e.setDataRetirada(new Date());
			e.setSala(s);
			
			list.add(e);
		}
		
		return list;
	}
	
	
}
