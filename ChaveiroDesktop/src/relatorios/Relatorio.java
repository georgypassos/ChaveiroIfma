package relatorios;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import util.Utilidades;

public abstract class Relatorio {

	private String fileJasper;
	private String filePdf = "";

	private static final String CAMINHO_RELATORIO = "/relatorios/";
	
	private Utilidades utilidades = Utilidades.getInstance();

	protected Relatorio(String f) {
		fileJasper = f;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected String  openPdf(Collection list, HashMap parametros, boolean open) {

		if (filePdf.trim().equalsIgnoreCase(""))
			setPdfName();

		try {

			InputStream relJasper = getClass().getResourceAsStream(CAMINHO_RELATORIO + fileJasper);
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(list);
			JasperPrint impressao = JasperFillManager.fillReport(relJasper, parametros, ds);

			File destFile = new File(Utilidades.getTempFolder(), filePdf);
			String destFileName = destFile.toString();

			JasperExportManager.exportReportToPdfFile(impressao, destFileName);

			if (open) {
				Runtime.getRuntime().exec("cmd /c start " + destFileName);
			}
			
			return destFileName;

		} catch (Exception e) {
			e.printStackTrace();
			
			utilidades.msgError("Erro ao tentar gerar relatorio");
			
			return null;
		}

	}

	private void setPdfName() {
		filePdf = fileJasper.substring(0, fileJasper.indexOf("."));
		filePdf += ".pdf";
	}

	public String getFileJasper() {
		return fileJasper;
	}

	public void setFileJasper(String fileJasper) {
		this.fileJasper = fileJasper;
	}

	public String getFilePdf() {
		return filePdf;
	}

	public void setFilePdf(String filePdf) {
		this.filePdf = filePdf;
	}

}
