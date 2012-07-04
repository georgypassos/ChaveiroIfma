package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

public class TelaTeste extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaTeste frame = new TelaTeste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaTeste() {
		setBounds(100, 100, 400, 403);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("Nome:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(57, 18, 70, 15);
		panel.add(label);
		
		JLabel label_1 = new JLabel("CPF:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(57, 58, 70, 15);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Email:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(57, 98, 70, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Telefone:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(57, 138, 70, 15);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Repetir senha:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(30, 218, 97, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Perfil:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(57, 262, 70, 15);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Senha:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(57, 178, 70, 14);
		panel.add(label_6);
		
		JButton button = new JButton("Novo");
		button.setBounds(10, 320, 93, 30);
		panel.add(button);
		
		JButton button_1 = new JButton("Salvar");
		button_1.setBounds(121, 321, 117, 30);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Cancelar");
		button_2.setBounds(256, 321, 117, 30);
		panel.add(button_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(137, 131, 157, 28);
		panel.add(textField);
		
		JFormattedTextField formattedTextField = new JFormattedTextField((AbstractFormatter) null);
		formattedTextField.setColumns(10);
		formattedTextField.setBounds(137, 51, 157, 28);
		panel.add(formattedTextField);
		
		textField_1 = new JTextField((Document) null, "", 10);
		textField_1.setColumns(10);
		textField_1.setBounds(137, 11, 157, 28);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(137, 91, 157, 28);
		panel.add(textField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(137, 255, 157, 28);
		panel.add(comboBox);
		
		passwordField = new JPasswordField((Document) null, "", 10);
		passwordField.setBounds(137, 171, 157, 28);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField((Document) null, "", 10);
		passwordField_1.setBounds(137, 211, 157, 28);
		panel.add(passwordField_1);

	}

}
