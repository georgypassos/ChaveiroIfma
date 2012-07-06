import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import telas.FrameLogin;
import dao.ClienteDAO;


public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao inicializar Look and Feel ...");
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteDAO.getInstance(); //FIXME mudar essa forma de inicializar o hibernate
					new FrameLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
