package br.foo.pa.report;

import java.awt.Color;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.ImageBanner;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import br.foo.pa.domain.Veiculo;

public class VeiculoReport {
	private final Collection<Veiculo> lista;
	
	
	public VeiculoReport(Collection<Veiculo> c) {
		lista = new ArrayList<>(c);

	}
	
	
	
	public JasperPrint getReport() throws ColumnBuilderException, JRException, ClassNotFoundException {
		Style headerStyle = createHeaderStyle();
		Style detailTextStyle = createDetailTextStyle();
		Style detailNumberStyle = createDetailNumberStyle();
		Style groupVariable = createGroupVariables();
		Style headerVariables = createHeadrStyleVariables();
		DynamicReport dr = getReport(headerStyle, detailTextStyle, detailNumberStyle,groupVariable,headerVariables);
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

	
	private AbstractColumn createColumn(String property, Class<?> type, String title, int width,Style detailStyle, Style headerStyle)
			throws ColumnBuilderException {
		return ColumnBuilder.getNew()
				.setColumnProperty(property, type.getName())
				.setTitle(title)
				.setWidth(Integer.valueOf(width))
				.setStyle(detailStyle)
				.setHeaderStyle(headerStyle)
				.build();
	}
	
	

	private DynamicReport getReport(Style headerStyle, Style detailTextStyle, Style detailNumStyle, Style groupVariables, Style headerVariables)
			throws ColumnBuilderException, ClassNotFoundException {

		Style importeStyle = new Style();
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
		
		Style titleStyleCidade = new Style();
		titleStyleCidade.setFont(new Font(14, Font._FONT_VERDANA, true));
		

		StyleBuilder subTitleStyle = new StyleBuilder(true);
		subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		subTitleStyle.setFont(new Font(Font.MEDIUM, null, true));
		
		Style groupTitleStyle = new Style();
		groupTitleStyle.setFont(Font.ARIAL_BIG);
		
		DynamicReportBuilder report = new DynamicReportBuilder();
		
		AbstractColumn colunaCode = createColumn("id", Long.class, "ID", 40, detailTextStyle,headerStyle);
		AbstractColumn colunaProduto = createColumn("marca", String.class, "Marca", 85,detailTextStyle, headerStyle);
		AbstractColumn colunaItem = createColumn("modelo", String.class, "Modelo", 85,detailTextStyle, headerStyle);
		
		AbstractColumn colunaEstado = ColumnBuilder.getNew()
				.setColumnProperty("estado", String.class.getName())
				.setTitle("State:").setWidth(new Integer(50))
				.setStyle(titleStyleCidade)
				.setHeaderStyle(groupTitleStyle)
				.build();
		
		
		AbstractColumn colunaValor = ColumnBuilder.getNew()
				.setColumnProperty("valor", Float.class.getName())
				.setTitle("Valor")
				.setWidth(new Integer(90))
				.setPattern("R$ 0.00")
				.setStyle(importeStyle)
				.setHeaderStyle(headerStyle).build();
		
		AbstractColumn colunaKM = ColumnBuilder.getNew()
				.setColumnProperty("km", Float.class.getName())
				.setTitle("Distância")
				.setWidth(new Integer(90))
				.setPattern("KM 0.00")
				.setStyle(importeStyle)
				.setHeaderStyle(headerStyle).build();

		report.addColumn(colunaCode)
		.addColumn(colunaProduto)
		.addColumn(colunaItem)
		.addColumn(colunaValor)
		.addColumn(colunaKM)
		.addColumn(colunaEstado);
		
		
		report.addGlobalFooterVariable(colunaValor, DJCalculation.SUM,groupVariables).setGrandTotalLegend("Total:");
		report.addGlobalFooterVariable(colunaCode,DJCalculation.SUM,groupVariables);
		report.addGlobalFooterVariable(colunaItem, DJCalculation.SUM,headerVariables)
		.setGlobalHeaderVariableHeight(new Integer(25))
		.setGlobalFooterVariableHeight(new Integer(25));
		
		GroupBuilder gb1 = new GroupBuilder();
		
		DJGroup g1 = gb1.setCriteriaColumn((PropertyColumn) colunaEstado)
					//.addFooterVariable(colunaCode,DJCalculation.SUM,groupVariables)
					.addFooterVariable(colunaValor,DJCalculation.SUM,groupVariables)
					.addFooterVariable(colunaItem,DJCalculation.SUM,groupVariables)
					.addFooterVariable(colunaProduto,DJCalculation.SUM,groupVariables)
					.addFooterVariable(colunaKM,DJCalculation.SUM,groupVariables)
					//.setGroupLayout(GroupLayout.VALUE_IN_HEADER_WITH_HEADERS_AND_COLUMN_NAME) 
					//.setGroupLayout(GroupLayout.VALUE_IN_HEADER)
					.build();
		
	/*	GroupBuilder gb2 = new GroupBuilder();
		DJGroup g2 = gb2.setCriteriaColumn((PropertyColumn) colunaProduto)
				.addFooterVariable(colunaCode,DJCalculation.SUM,groupVariables)
				.addFooterVariable(colunaValor,DJCalculation.SUM,groupVariables)
				.addFooterVariable(colunaItem,DJCalculation.SUM,groupVariables)
				.addFooterVariable(colunaProduto,DJCalculation.SUM,groupVariables)
				.addFooterVariable(colunaKM,DJCalculation.SUM,groupVariables)
				.setGroupLayout(GroupLayout.VALUE_IN_HEADER_WITH_HEADERS_AND_COLUMN_NAME) 
				//.setGroupLayout(GroupLayout.VALUE_IN_HEADER)
				.build();*/
		
		//report.addGroup(g2);
		report.addGroup(g1); 
		
		report.setTitle("Poder Judiciário da União ")
		.setSubtitle("Tribunal Regional Eleitoral do Pará " 
		+ "\\n" +" Relatório de Veículos Cadastrados em "+ Year.now() )
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
		
		return report.build();
		
	}
}
