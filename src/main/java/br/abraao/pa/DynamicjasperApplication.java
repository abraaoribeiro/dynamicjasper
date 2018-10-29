package br.abraao.pa;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
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

		VendaRepository vendaRepository = new VendaRepository();
		
		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.setTitle(" Outubro de 2018 ")		//defines the title of the report
		        .setSubtitle("A lista de produtos "
		                        +"Contendo varias marcas")
		        .setDetailHeight(15)		//defines the height for each record of the report
		        .setMargins(30, 20, 30, 15)		//define the margin space for each side (top, bottom, left and right)
		        .setColumnsPerPage(1);		//defines columns per page (like in the telephone guide)

		AbstractColumn columnCode = ColumnBuilder.getNew()
				.setColumnProperty("id", Long.class.getName())
				.setTitle("ID").setWidth(40)
				.build();

		AbstractColumn columnaProduto = ColumnBuilder.getNew()
		        .setColumnProperty("produto", String.class.getName())
		        .setTitle("protudo").setWidth(85)
		        .build();

		AbstractColumn columnaItem = ColumnBuilder.getNew()
		        .setColumnProperty("marca", String.class.getName())
		        .setTitle("Marca").setWidth(85)
		        .build();


		
		drb.addColumn(columnCode);
		drb.addColumn(columnaProduto);
		drb.addColumn(columnaItem);
		
		drb.setUseFullPageWidth(true);
		DynamicReport dr = drb.build(); //Finally build the report!
		
		

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
