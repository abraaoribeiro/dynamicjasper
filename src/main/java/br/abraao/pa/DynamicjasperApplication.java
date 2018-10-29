package br.abraao.pa;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import br.abraao.pa.repository.VendaRepository;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@SpringBootApplication
public class DynamicjasperApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DynamicjasperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("====================");
		
		Style groupVariables = new Style("groupVariables");
		groupVariables.setFont(Font.ARIAL_MEDIUM_BOLD);
		groupVariables.setTextColor(Color.BLUE);
		groupVariables.setBorderBottom(Border.THIN());
		groupVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
		groupVariables.setVerticalAlign(VerticalAlign.BOTTOM);
		
		Style headerStyle = new Style("header");
		headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerStyle.setBorderBottom(Border.PEN_1_POINT());
		headerStyle.setBackgroundColor(Color.gray);
		headerStyle.setTextColor(Color.white);
		headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		headerStyle.setTransparency(Transparency.OPAQUE);
		
		Style headerVariables = new Style("headerVariables");
		headerVariables.setFont(Font.ARIAL_BIG_BOLD);
		headerVariables.setBorderBottom(Border.THIN());
		headerVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
		headerVariables.setVerticalAlign(VerticalAlign.TOP);
		headerVariables.setStretchWithOverflow(true);

		Style titleStyle = new Style("titleStyle");
		titleStyle.setFont(new Font(18, Font._FONT_VERDANA, true));
		Style importeStyle = new Style();
		importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
		Style oddRowStyle = new Style();
		oddRowStyle.setBorder(Border.NO_BORDER());
		oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
		oddRowStyle.setTransparency(Transparency.OPAQUE);
		
		VendaRepository vendaRepository = new VendaRepository();
		
		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.setTitle(" Outubro de 2018 ")		
		        .setSubtitle("Lista de Produtos")
		        .setDetailHeight(15)		
		        .setMargins(30, 20, 30, 15)		
		        .setColumnsPerPage(1);		

		AbstractColumn colunaCode = ColumnBuilder.getNew()
				.setColumnProperty("id", Long.class.getName())
				.setTitle("ID").setWidth(40)
				.build();

		AbstractColumn colunaProduto = ColumnBuilder.getNew()
		        .setColumnProperty("produto", String.class.getName())
		        .setTitle("Protudo").setWidth(85)
		        .build();

		AbstractColumn colunaItem = ColumnBuilder.getNew()
		        .setColumnProperty("marca", String.class.getName())
		        .setTitle("Marca").setWidth(85)
		        .build();
		

		AbstractColumn colunaValor = ColumnBuilder.getNew()
		        .setColumnProperty("valor", Float.class.getName())
		        .setTitle("Valor").setWidth(90)
		        .setPattern("$ 0.00")		
		        .build();
		
		drb.addGlobalHeaderVariable(colunaValor, DJCalculation.SUM,headerVariables);
		drb.setGlobalFooterVariableHeight(new Integer(25));
		drb.setGlobalHeaderVariableHeight(new Integer(25));
		
		GroupBuilder gb1 = new GroupBuilder();
		DJGroup g1 = gb1.setCriteriaColumn((PropertyColumn) colunaValor)		
				.addHeaderVariable(colunaValor,DJCalculation.SUM,groupVariables)
				.setFooterVariablesHeight(new Integer(20))
				.setFooterHeight(new Integer(50),true)
				.setHeaderVariablesHeight(new Integer(35))
			 	.build();
				 
		drb.addColumn(colunaCode);
		drb.addColumn(colunaProduto);
		drb.addColumn(colunaItem);
		drb.addColumn(colunaValor);
		drb.addGroup(g1);
		
		drb.setUseFullPageWidth(true);
		drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_SLASH_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT);
		DynamicReport dr = drb.build(); 
		
		

		JRDataSource ds = new JRBeanCollectionDataSource(vendaRepository.findAll());
		JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
		SimpleExporterInput simpleExporterInput = new SimpleExporterInput(jp);

		FileOutputStream fos = new FileOutputStream(new File("target/relatorio.pdf"));
		OutputStreamExporterOutput simpleOutputStreamExporterOutput = new SimpleOutputStreamExporterOutput(fos);

		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setExporterInput(simpleExporterInput);
		exporter.setExporterOutput(simpleOutputStreamExporterOutput);

		exporter.exportReport();

	}

}
