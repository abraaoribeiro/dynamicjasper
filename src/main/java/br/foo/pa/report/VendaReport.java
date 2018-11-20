package br.foo.pa.report;

import java.awt.Color;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.ImageBanner;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import br.foo.pa.domain.Venda;

public class VendaReport {

	private final Collection<Venda> lista;
	
	
	public VendaReport(Collection<Venda> c) {
		lista = new ArrayList<>(c);

	}
	
	
	/**
	 * 
	 * @return JasperPrint
	 * @throws ColumnBuilderException
	 * @throws JRException
	 * @throws ClassNotFoundException
	 */
	public JasperPrint getReportf() throws ColumnBuilderException, JRException, ClassNotFoundException {
		//Style headerStyle = createHeaderStyle();
//		Style detailTextStyle = createDetailTextStyle();
//		Style detailNumberStyle = createDetailNumberStyle();
//		Style groupVariable = createGroupVariables();
//		Style headerVariables = createHeadrStyleVariables();
		//DynamicReport dr = getReport(headerStyle, detailTextStyle, detailNumberStyle,groupVariable,headerVariables);
		DynamicReport dr = getReport();

		
		JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(),
				new JRBeanCollectionDataSource(lista));
		return jp;

	}
	
	private Style createHeaderStyle() {		
		return new StyleBuilder(true)
				.setFont(Font.ARIAL_MEDIUM_BOLD)
				.setBorderBottom(Border.PEN_1_POINT())
				.setBackgroundColor(Color.WHITE)
				.setTextColor(Color.black)
				.setHorizontalAlign(HorizontalAlign.CENTER)
				.setVerticalAlign(VerticalAlign.MIDDLE)
				.setTransparency(Transparency.OPAQUE)
				.build();
		
	}
	private Style createHeadrStyleVariables() {
		return new StyleBuilder(true)
				.setFont(Font.ARIAL_MEDIUM_BOLD)
				.setHorizontalAlign(HorizontalAlign.RIGHT)
				.setVerticalAlign(VerticalAlign.MIDDLE)
				.build();
		
	}

	private Style createDetailTextStyle() {
		return new StyleBuilder(true)
				.setHorizontalAlign(HorizontalAlign.CENTER)
				.setVerticalAlign(VerticalAlign.BOTTOM)
				.build();
	}

	private Style createDetailNumberStyle() {
		return new StyleBuilder(true)
				.setHorizontalAlign(HorizontalAlign.CENTER)
				.setVerticalAlign(VerticalAlign.BOTTOM)
				.setPattern("R$0.00")
				.build();
	}
	
	private Style createGroupVariables() {
		return new StyleBuilder(true)
				.setHorizontalAlign(HorizontalAlign.CENTER)
				.setBorder(Border.NO_BORDER())      
				.setBackgroundColor(Color.LIGHT_GRAY)     
				.setTransparency(Transparency.OPAQUE)     
				.build();

		
	}

/*	
	private AbstractColumn createColumn(String property,Class<?> type, String title, int width,Style detailStyle, Style headerStyle)
			throws ColumnBuilderException {
		return ColumnBuilder.getNew()
				.setColumnProperty(property, type.getName())
				.setTitle(title)
				.setWidth(Integer.valueOf(width))
				.setStyle(detailStyle)
				.setHeaderStyle(headerStyle)
				.build();
	}
	*/
	
	/**
	 * 
	 * @param headerStyle
	 * @param detailTextStyle
	 * @param detailNumStyle
	 * @param groupVariables
	 * @param headerVariables
	 * @return DynamicReport
	 * @throws ColumnBuilderException
	 * @throws ClassNotFoundException
	 */

	/*private DynamicReport getReport(Style headerStyle, Style detailTextStyle, Style detailNumStyle, Style groupVariables, Style headerVariables)
			throws ColumnBuilderException, ClassNotFoundException {*/
	
	private DynamicReport getReport()throws ColumnBuilderException, ClassNotFoundException {
		DynamicReportBuilder report = new DynamicReportBuilder();
		/*Style importeStyle = new Style();
		importeStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		
		Style oddRowStyle = new Style();
		oddRowStyle.setBorder(Border.NO_BORDER());
		oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
		oddRowStyle.setTransparency(Transparency.OPAQUE);
		
		Style detailStyle = new Style();
		detailStyle.setVerticalAlign(VerticalAlign.TOP);
		
		StyleBuilder titleStyle = new StyleBuilder(true);
		titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		titleStyle.setVerticalAlign(VerticalAlign.BOTTOM);
		titleStyle.setFont(new Font(12, null, true));
		
		Integer margin = new Integer(30);

		StyleBuilder subTitleStyle = new StyleBuilder(true);
		subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		subTitleStyle.setFont(new Font(Font.MEDIUM, null, true));
	
		*/
		
		Style detailStyle = new Style();
		
		Style headerStyle = new Style();
		headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerStyle.setBorderBottom(Border.PEN_1_POINT());
		headerStyle.setBackgroundColor(Color.gray);
		headerStyle.setTextColor(Color.white);
		headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		headerStyle.setTransparency(Transparency.OPAQUE);
		
		Style headerVariables = new Style();
		headerVariables.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerVariables.setBorderBottom(Border.THIN());
		headerVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
		headerVariables.setVerticalAlign(VerticalAlign.MIDDLE);
		
		Style titleStyle = new Style();
		titleStyle.setFont(new Font(18, Font._FONT_VERDANA, true));
		Style importeStyle = new Style();
		importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);
		Style oddRowStyle = new Style();
		oddRowStyle.setBorder(Border.NO_BORDER());
		oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
		oddRowStyle.setTransparency(Transparency.OPAQUE);
		
		Integer margin = new Integer(20);
			
			report.setTitleStyle(titleStyle)
			.setTitle("November " +Year.now() +" sales report")					//defines the title of the report
			.setSubtitle("The items in this report correspond "
					+"to the main products: DVDs, Books, Foods and Magazines")
			.setDetailHeight(new Integer(15)).setLeftMargin(margin)
			.setRightMargin(margin).setTopMargin(margin).setBottomMargin(margin)
			.setPrintBackgroundOnOddRows(false)
			.setGrandTotalLegend("Grand Total")
			.setGrandTotalLegendStyle(headerVariables)
			.setDefaultStyles(titleStyle, null, headerStyle, detailStyle)
			.setPrintColumnNames(true)
			.setOddRowBackgroundStyle(oddRowStyle)
			.addImageBanner(System.getProperty("user.dir")
					+"/src/main/resources/BOOT-INF/images/logo.png", new Integer(100), new Integer(50), ImageBanner.ALIGN_CENTER);
	
		
		

		// Definiçõa das Colunas
		
		/*AbstractColumn colunaProduto = createColumn("produto", String.class, "Produto", 85,detailTextStyle, headerStyle);
		AbstractColumn colunaItem = createColumn("item", String.class, "Item", 85,detailTextStyle, headerStyle);
		AbstractColumn colunaCode = createColumn("id", Long.class, "ID", 40, detailTextStyle,headerStyle);*/
		/*
		AbstractColumn colunaValor = ColumnBuilder.getNew()
				.setColumnProperty("valor", Float.class.getName())
				.setTitle("Valor")
				.setWidth(new Integer(90))
				.setPattern("R$ 0.00")
				.setStyle(importeStyle)
				.setHeaderStyle(headerStyle).build();*/
		
		
		AbstractColumn columnState = ColumnBuilder.getNew()
					.setColumnProperty("state", String.class.getName())
					.setTitle("State").setWidth(new Integer(85))
					//.setStyle(titleStyle).setHeaderStyle(titleStyle)
					.build();
	
			AbstractColumn columnBranch = ColumnBuilder.getNew()
					.setColumnProperty("branch", String.class.getName())
					.setTitle("Branch").setWidth(new Integer(85))
					.setStyle(detailStyle).setHeaderStyle(headerStyle)
					.build();
	
			AbstractColumn columnaProductLine = ColumnBuilder.getNew()
					.setColumnProperty("productLine", String.class.getName())
					.setTitle("Product Line").setWidth(new Integer(85))
					.setStyle(detailStyle).setHeaderStyle(headerStyle)
					.build();
	
			AbstractColumn columnaItem = ColumnBuilder.getNew()
					.setColumnProperty("item", String.class.getName())
					.setTitle("Item").setWidth(new Integer(85))
					.setStyle(detailStyle).setHeaderStyle(headerStyle)
					.build();
	
			AbstractColumn columnCode = ColumnBuilder.getNew()
					.setColumnProperty("id", Long.class.getName())
					.setTitle("ID").setWidth(new Integer(40))
					.setStyle(importeStyle).setHeaderStyle(headerStyle)
					.build();
	
			AbstractColumn columnaQuantity = ColumnBuilder.getNew()
					.setColumnProperty("quantity", Long.class.getName())
					.setTitle("Quantity").setWidth(new Integer(80))
					.setStyle(importeStyle).setHeaderStyle(headerStyle)
					.build();
	
			AbstractColumn columnAmount = ColumnBuilder.getNew()
					.setColumnProperty("amount", Float.class.getName())
					.setTitle("Amount").setWidth(new Integer(90)).setPattern("$ 0.00")
					.setStyle(importeStyle).setHeaderStyle(headerStyle)
					.build();
		
		
		
			
			/*report.addColumn(columnState)
			.addColumn(columnBranch)
			.addColumn(columnaProductLine)
			.addColumn(columnaItem)
			.addColumn(columnCode)
			.addColumn(columnaQuantity)
			.addColumn(columnAmount);
	*/
		/*// Criação do grupo gb1
		
		report.addGlobalFooterVariable(colunaValor, DJCalculation.SUM,groupVariables).setGrandTotalLegend("Total:");
		report.addGlobalFooterVariable(colunaCode,DJCalculation.SUM,groupVariables);
		report.addGlobalFooterVariable(colunaItem, DJCalculation.SUM,headerVariables)
		.setGlobalHeaderVariableHeight(new Integer(25))
		.setGlobalFooterVariableHeight(new Integer(25));*/
		

		/*GroupBuilder gb1 = new GroupBuilder();
		DJGroup g1 = gb1.setCriteriaColumn((PropertyColumn) colunaCode)
					//.addFooterVariable(colunaCode,DJCalculation.SUM,groupVariables)
					.addFooterVariable(colunaValor,DJCalculation.SUM,groupVariables)
					.addFooterVariable(colunaProduto,DJCalculation.SUM,groupVariables)
					.addFooterVariable(colunaItem,DJCalculation.SUM,groupVariables)
					//.setGroupLayout(GroupLayout.VALUE_IN_HEADER)
					.setGroupLayout(GroupLayout.VALUE_FOR_EACH)
					.setFooterVariablesHeight(new Integer(20))
					.setFooterHeight(new Integer(50),true)
					.setHeaderVariablesHeight(new Integer(35))
					.build();
		*/
		
		
			GroupBuilder gb1 = new GroupBuilder();
			
			 //define the criteria column to group by (columnState)
			DJGroup g1 = gb1.setCriteriaColumn((PropertyColumn) columnState)
					.addFooterVariable(columnAmount,DJCalculation.SUM,headerVariables) // tell the group place a variable footer of the column "columnAmount" with the SUM of allvalues of the columnAmount in this group.
					.addFooterVariable(columnaQuantity,DJCalculation.SUM,headerVariables) // idem for the columnaQuantity column
					.setGroupLayout(GroupLayout.VALUE_IN_HEADER)// tells the group how to be shown, there are manyposibilities, see the GroupLayout for more.
					.build();
			/*
			GroupBuilder gb2 = new GroupBuilder(); // Create another group (using another column as criteria)
			DJGroup g2 = gb2.setCriteriaColumn((PropertyColumn) columnBranch) // and we add the same operations for the columnAmount and
								.addFooterVariable(columnAmount,DJCalculation.SUM) // columnaQuantity columns
								.addFooterVariable(columnaQuantity,	DJCalculation.SUM)
								.build();*/
			
			report.addColumn(columnState);
			report.addColumn(columnBranch);
			report.addColumn(columnaProductLine);
			report.addColumn(columnaItem);
			report.addColumn(columnCode);
			report.addColumn(columnaQuantity);
			report.addColumn(columnAmount);
			
			report.addGroup(g1); // add group g1
			//report.addGroup(g2); // add group g2
			
			report.setUseFullPageWidth(true);
			report.addAutoText(AutoText.AUTOTEXT_PAGE_X_SLASH_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT);

			
			
			
		/*
		report.addGroup(g1);
		
		// Criando o cabeçalho do relatório
		
		report.setTitle("")
		.setSubtitle("") 	
		+ "\\n" +" Relatório de Vendas Realizadas em "+ Year.now() )
		.setTitleStyle(titleStyle.build())
		.setDetailHeight(new Integer(15)).setLeftMargin(margin)
		.setRightMargin(margin).setTopMargin(margin).setBottomMargin(margin)
		.setSubtitleStyle(subTitleStyle.build())
		.setPrintBackgroundOnOddRows(false)
		.setUseFullPageWidth(true)
		.setGrandTotalLegendStyle(headerVariables)
		.setPrintColumnNames(true)
		.setOddRowBackgroundStyle(oddRowStyle)
		.addImageBanner(System.getProperty("user.dir")
				+"/src/main/resources/BOOT-INF/images/logo.png", new Integer(100), new Integer(50), ImageBanner.ALIGN_CENTER);
		
		report.setUseFullPageWidth(true);
		report.addAutoText(AutoText.AUTOTEXT_PAGE_X_SLASH_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT);
		*/
		return report.build();
		
	}
	

}
