package relatorios;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import util.Utilidades;
import excecoes.SistemaException;

public abstract class Relatorio {

	private String fileJasper;
	private String filePdf = "";

	private static final String CAMINHO_RELATORIO = "/relatorios/";
	
	private Utilidades utilidades = Utilidades.getInstance();

	protected Relatorio(String f) {
		fileJasper = f;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void openJasper(Collection list, HashMap parametros) throws SistemaException{
		
		try {

			InputStream relJasper = getClass().getResourceAsStream(CAMINHO_RELATORIO + fileJasper);
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(list);
			JasperPrint impressao = JasperFillManager.fillReport(relJasper, parametros, ds);

			JasperViewer viewer = new JasperViewer(impressao, false);

	        viewer.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new SistemaException("Erro ao tentar gerar relatorio");
		}
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected String openPdf(Collection list, HashMap parametros, String nomeArquivo) throws SistemaException{

		if (filePdf.trim().equalsIgnoreCase(""))
			setPdfName(nomeArquivo);

		try {

			InputStream relJasper = getClass().getResourceAsStream(CAMINHO_RELATORIO + fileJasper);
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(list);
			JasperPrint impressao = JasperFillManager.fillReport(relJasper, parametros, ds);

			File destFile = new File(Utilidades.getTempFolder(), filePdf);
			String destFileName = destFile.toString();
						
			JasperExportManager.exportReportToPdfFile(impressao, destFileName);

			Runtime.getRuntime().exec("cmd /c start " + destFileName);
			
			return destFileName;

		} catch (Exception e) {
			e.printStackTrace();
			
			throw new SistemaException("Erro ao tentar gerar relatorio");
		}

	}
	
	private void setPdfName(String nomeArquivo) {
		filePdf = fileJasper.substring(0, fileJasper.indexOf("."));
		
		if(nomeArquivo != null)
			filePdf += "-"+nomeArquivo+"-";
		
		filePdf = filePdf.replaceAll(" ", "");
		
		String data = utilidades.getData(new java.util.Date(), "dd.MM.yyyy-HH.mm.ss");
		
		filePdf += data;
		
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
