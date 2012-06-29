package util;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Utilidades {

	private static final Utilidades utilidades = new Utilidades();

	private Utilidades() {
	}

	public static Utilidades getInstance() {

		return utilidades;
	}

	// formata um "JInternalFrame"
	public void formataJanela(javax.swing.JInternalFrame gui, String srcImagem) {
		gui.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(srcImagem)));
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		java.awt.Dimension frameSize = gui.getSize();
		int x = (screenSize.width - frameSize.width) / 2;
		gui.setLocation(x, 10);
	}

	public int getYesNoOption(String msg) {
		String yn[] = new String[] { "Sim", "Não" };

		return JOptionPane.showOptionDialog(null, msg, "AVISO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
				new ImageIcon(getClass().getResource("/imagens/imgpergunta.png")), yn, yn[0]);
	}

	public void msgInformation(String msg) {
		JOptionPane.showMessageDialog(null, msg, "AVISO", JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(getClass().getResource("/imagens/imginfo.png")));

	}

	public void msgWarning(String msg) {
		JOptionPane.showMessageDialog(null, msg, "ATENÇÃO", JOptionPane.WARNING_MESSAGE,
				new ImageIcon(getClass().getResource("/imagens/imgatencao.png")));
	}

	public void msgError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "ERRO", JOptionPane.ERROR_MESSAGE,
				new ImageIcon(getClass().getResource("/imagens/imgerro.png")));
	}

	public String getDadoJOption(String msg, String titulo) {
		return String.valueOf(JOptionPane.showInputDialog(null, msg, titulo, JOptionPane.OK_CANCEL_OPTION, new ImageIcon(
				getClass().getResource("/imagens/imgpergunta.png")), null, null));
	}

}
