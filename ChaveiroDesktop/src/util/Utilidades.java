package util;

import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import telas.MyInternalFrame;

public class Utilidades {

	private static final Utilidades utilidades = new Utilidades();

	private Utilidades() {
	}

	public static Utilidades getInstance() {

		return utilidades;
	}

	public ImageIcon imgBtSalaAberta = new ImageIcon(getClass().getResource("/imagens/lock-open.png"));
	public ImageIcon imgBtSalaFechada = new ImageIcon(getClass().getResource("/imagens/lock-close.png"));
//	public ImageIcon imgBtSalaIndisponivel = new ImageIcon(getClass().getResource("/imagens/lock-disable.png"));

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
		centralizaJanela(gui, 40);

		gui.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	}

	public void centralizaJanela(java.awt.Component component, int y) {
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		java.awt.Dimension frameSize = component.getSize();
		int x = (screenSize.width - frameSize.width) / 2;
		component.setLocation(x, y);
	}

	public void limparCampos(MyInternalFrame component){
		for(Component c: component.getComponents()){    
            if(c instanceof JTextField){    
               ((JTextField) c).setText(null);                   
            }  
            if(c instanceof JFormattedTextField){  
                ((JFormattedTextField) c).setText(null);  
            }  
            if(c instanceof JTextArea){  
               ((JTextArea) c).setText(null);                      
            }
		}
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
	
	public boolean validacpf(String strCpf) { // formato XXX.XXX.XXX-XX

		if (!strCpf.substring(0, 1).equals("")) {
			try {
//				boolean validado = true;
				int d1, d2;
				int digito1, digito2, resto;
				int digitoCPF;

				String nDigResult;
				strCpf = strCpf.replace('.', ' ');
				strCpf = strCpf.replace('-', ' ');
				strCpf = strCpf.replaceAll(" ", "");
				d1 = d2 = 0;
				digito1 = digito2 = resto = 0;

				for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
					digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();

					// multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
					d1 = d1 + (11 - nCount) * digitoCPF;

					// para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
					d2 = d2 + (12 - nCount) * digitoCPF;
				}

				// Primeiro resto da divisão por 11.
				resto = (d1 % 11);

				// Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
				if (resto < 2)
					digito1 = 0;
				else
					digito1 = 11 - resto;

				d2 += 2 * digito1;

				// Segundo resto da divisão por 11.
				resto = (d2 % 11);

				// Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
				if (resto < 2)

					digito2 = 0;

				else
					digito2 = 11 - resto;

				// Digito verificador do CPF que está sendo validado.
				String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());

				// Concatenando o primeiro resto com o segundo.
				nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

				// comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
				return nDigVerific.equals(nDigResult);

			} catch (Exception e) {
				System.err.println("Erro !" + e);

				return false;
			}
		} else
			return false;
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
