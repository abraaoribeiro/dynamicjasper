package br.abraao.pa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynamicjasperApplication  {

	public static void main(String[] args) {
		SpringApplication.run(DynamicjasperApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		System.out.println("======================");
		
		VendaRepository vendaRepository = new VendaRepository();
		
		Style headerStyle = new Style("header");
		headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerStyle.setBorderBottom(Border.PEN_2_POINT());
		headerStyle.setBackgroundColor(Color.DARK_GRAY);
		headerStyle.setTextColor(Color.white);
		headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		headerStyle.setTransparency(Transparency.OPAQUE);
		
		Style headerVariables = new Style("headerVariables");
		headerVariables.setFont(Font.ARIAL_BIG_BOLD);
		//headerVariables.setBorderBottom(Border.THIN());
		headerVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
		headerVariables.setVerticalAlign(VerticalAlign.MIDDLE);
		headerVariables.setStretchWithOverflow(true);
		
		Style groupVariables = new Style("groupVariables");
		groupVariables.setFont(Font.ARIAL_MEDIUM_BOLD);
		groupVariables.setHorizontalAlign(HorizontalAlign.CENTER);
		groupVariables.isOverridesExistingStyle();
		groupVariables.setVerticalAlign(VerticalAlign.MIDDLE);
		groupVariables.getPaddingBottom();
		
	
		
		Style detailStyle = new Style("detail");		
		Style subtitleStyle = new Style();
		Style amountStyle = new Style(); amountStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
		
		Style titleStyle = new Style("titleStyle");
		titleStyle.setFont(new Font(18, Font._FONT_VERDANA, true));
		
		Style importeStyle = new Style();
		importeStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		
		Style oddRowStyle = new Style();
		oddRowStyle.setBorder(Border.NO_BORDER()); 
		oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
		oddRowStyle.setTransparency(Transparency.OPAQUE);
		
		DynamicReportBuilder drb = new DynamicReportBuilder();
		Integer margin = new Integer(20);
		drb.setTitleStyle(titleStyle)
		.setTitle("Data " + LocalDate.now())					
		.setSubtitle("Lista de Produtos")
		.setDetailHeight(15)
		.setLeftMargin(margin)
		.setRightMargin(margin)
		.setTopMargin(margin)
		.setBottomMargin(margin)					
		.setDefaultStyles(titleStyle, null, headerStyle, detailStyle)
		.setColumnsPerPage(1)
		.setOddRowBackgroundStyle(oddRowStyle);
			
		
		
		AbstractColumn colunaProduto = ColumnBuilder.getNew()
				.setColumnProperty("produto", String.class.getName())
				.setTitle("Produto")
				.setWidth(new Integer(85))
				.setStyle(importeStyle)
				.setHeaderStyle(headerStyle)
				.build();
		
		AbstractColumn colunaItem = ColumnBuilder.getNew()
				.setColumnProperty("item", String.class.getName()).setTitle("Item")
				.setWidth(new Integer(85))
				.setStyle(importeStyle)
				.setHeaderStyle(headerStyle)
				.build();
		
		AbstractColumn colunaCode = ColumnBuilder.getNew()
				.setColumnProperty("id", Long.class.getName())
				.setTitle("ID")
				.setWidth(new Integer(40))
				.setStyle(importeStyle)
				.setHeaderStyle(headerStyle)
				.build();
		AbstractColumn colunaValor = ColumnBuilder.getNew()
				.setColumnProperty("valor", Float.class.getName()).setTitle("Valor")
				.setWidth(new Integer(100))
				.setPattern("$ 0.00")
				.setStyle(importeStyle)
				.setHeaderStyle(headerStyle)
				.build();
	
	
		drb.addGlobalFooterVariable(colunaValor, DJCalculation.SUM,groupVariables).setGrandTotalLegend("Total:");
		drb.addGlobalFooterVariable(colunaCode, DJCalculation.SUM,groupVariables);
		//drb.setGlobalHeaderVariableHeight(new Integer(25));
		//drb.setGlobalFooterVariableHeight(new Integer(100));
		
		GroupBuilder gb1 = new GroupBuilder();
		
		DJGroup g1 = gb1.setCriteriaColumn((PropertyColumn) colunaValor)
				.addFooterVariable(colunaValor,DJCalculation.SUM,groupVariables)
				//.addHeaderVariable(colunaItem,DJCalculation.SUM,groupVariables)
				//.addHeaderVariable(colunaValor,DJCalculation.SUM,groupVariables)
				//.addHeaderVariable(colunaItem,DJCalculation.SUM,groupVariables)
				//.setGroupLayout(GroupLayout.VALUE_IN_HEADER)
				//.setGroupLayout(GroupLayout.DEFAULT_WITH_HEADER)
				.setFooterHeight(new Integer(50),true)
				.setFooterVariablesHeight(new Integer(20))
				.setHeaderVariablesHeight(new Integer(35))
				.build();
		
		GroupBuilder gb2 = new GroupBuilder(); // Create another group (using another column as criteria)
		DJGroup g2 = gb2.setCriteriaColumn((PropertyColumn) columnaProduto) // and we add the same operations for the columnAmount and
		.addFooterVariable(columnValor,
		DJCalculation.SUM) // columnaQuantity columns
		.addFooterVariable(columnValor,	DJCalculation.SUM)
		.build();
		
		
		drb.addColumn(colunaProduto);
		drb.addColumn(colunaItem);
		drb.addColumn(colunaCode);
		drb.addColumn(colunaValor);
		
		//drb.addGroup(g1); // add group g1
		//drb.addGroup(g2);
		
		//drb.setPrintColumnNames (false);
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
	 */
}
