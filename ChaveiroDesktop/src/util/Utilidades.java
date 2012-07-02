package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class Utilidades {

	private static final Utilidades utilidades = new Utilidades();

	private Utilidades() {
	}

	public static Utilidades getInstance() {

		return utilidades;
	}

	public ImageIcon imgBtSalaAberta = new ImageIcon(getClass().getResource("/imagens/lock-open.png"));
	public ImageIcon imgBtSalaFechada = new ImageIcon(getClass().getResource("/imagens/lock-close.png"));

	public Object getValueFromCombo(javax.swing.JComboBox combo) {
		javax.swing.ComboBoxModel model = combo.getModel();

		Object value;
		if (model instanceof MyModelComboBox) {
			MyModelComboBox myModelComboBox = (MyModelComboBox) model;
			value = myModelComboBox.getSelectedKey();
		} else {
			value = combo.getSelectedItem();
		}

		return value;
	}
	
	public MaskFormatter mascara(String msk) {
        MaskFormatter form = null;

        try {
            form = new MaskFormatter(msk);
        } catch (java.text.ParseException exc) {
        }

        return form;
    }

	// formata um "JInternalFrame"
	public void formataJanela(javax.swing.JInternalFrame gui, String srcImagem) {
		try {
			gui.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource(srcImagem)));
		} catch (Exception e) {
			System.out.println("Erro ao colocar imagem na janela...");
		}
		centralizaJanela(gui);
		
		gui.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	}
	
	public void centralizaJanela(java.awt.Component component){
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		java.awt.Dimension frameSize = component.getSize();
		int x = (screenSize.width - frameSize.width) / 2;
		component.setLocation(x, 40);
	}

	public Date getData(String data, String inType, String outType) {

		try {
			SimpleDateFormat inFmt = new SimpleDateFormat(inType);
			SimpleDateFormat outFmt = new SimpleDateFormat(outType);

			return outFmt.parse(outFmt.format(inFmt.parse(data)));

		} catch (ParseException ex) {
		}

		return null;
	}

	public String getData(Date data, String type) {

		SimpleDateFormat fmt = new SimpleDateFormat(type);

		return fmt.format(data);
	}

	public String getHtml(String msg) {

		return "<html>" + msg + "</html>";
	}

	public int getYesNoOption(String msg) {
		String yn[] = new String[] { "Sim", "Não" };

		return JOptionPane.showOptionDialog(null, msg, "AVISO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
				new ImageIcon(getClass().getResource("/imagens/imgpergunta.png")), yn, yn[0]);
	}

	public void msgInformation(String msg) {
		JOptionPane.showMessageDialog(null, msg, "AVISO", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass()
				.getResource("/imagens/imginfo.png")));

	}

	public void msgWarning(String msg) {
		JOptionPane.showMessageDialog(null, msg, "ATENÇÃO", JOptionPane.WARNING_MESSAGE, new ImageIcon(getClass()
				.getResource("/imagens/imgatencao.png")));
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
