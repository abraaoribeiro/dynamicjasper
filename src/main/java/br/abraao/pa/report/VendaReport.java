package br.abraao.pa.report;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import br.abraao.pa.domain.Venda;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



public class VendaReport {

	private final Collection<Venda> lista;
	
	
	public VendaReport(Collection<Venda> c) {
		lista = new ArrayList<>(c);

	}
	
	/*@Autowired
	private VendaRepository vendaRepository;*/
	
	
	public JasperPrint getReport() throws ColumnBuilderException, JRException, ClassNotFoundException {
		Style headerStyle = createHeaderStyle();
		Style detailTextStyle = createDetailTextStyle();
		Style detailNumberStyle = createDetailNumberStyle();
		DynamicReport dr = getReport(headerStyle, detailTextStyle, detailNumberStyle);
		JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(),
				new JRBeanCollectionDataSource(lista));
		return jp;

	}
	
	
	private Style createHeaderStyle() {		
		return new StyleBuilder(true)
				.setFont(Font.VERDANA_MEDIUM_BOLD)
				.setBorder(Border.THIN())
				.setBorderBottom(Border.PEN_2_POINT())
				.setBorderColor(Color.BLACK)
				.setBackgroundColor(Color.DARK_GRAY)
				.setTextColor(Color.BLACK)
				.setHorizontalAlign(HorizontalAlign.CENTER)
				.setVerticalAlign(VerticalAlign.MIDDLE)
				.setTransparency(Transparency.OPAQUE)
				.build();
	}

	private Style createDetailTextStyle() {
		return new StyleBuilder(true)
				.setFont(Font.VERDANA_MEDIUM)
				.setBorder(Border.DOTTED())
				.setBorderColor(Color.BLACK)
				.setTextColor(Color.BLACK)
				.setHorizontalAlign(HorizontalAlign.LEFT)
				.setVerticalAlign(VerticalAlign.MIDDLE)
				.setPaddingLeft(5)
				.build();
	}

	private Style createDetailNumberStyle() {
		return new StyleBuilder(true)
				.setFont(Font.VERDANA_MEDIUM)
				.setBorder(Border.DOTTED())
				.setBorderColor(Color.BLACK)
				.setTextColor(Color.BLACK)
				.setHorizontalAlign(HorizontalAlign.RIGHT)
				.setVerticalAlign(VerticalAlign.MIDDLE)
				.setPaddingRight(5)
				.setPattern("#,##0.00")
				.build();
	}

	
	private AbstractColumn createColumn(String property, Class<?> type, String title, int width, Style headerStyle, Style detailStyle)
			throws ColumnBuilderException {
		return ColumnBuilder.getNew()
				.setColumnProperty(property, type.getName())
				.setTitle(title)
				.setWidth(Integer.valueOf(width))
				.setStyle(detailStyle)
				.setHeaderStyle(headerStyle)
				.build();
	}
	
	

	private DynamicReport getReport(Style headerStyle, Style detailTextStyle, Style detailNumStyle)
			throws ColumnBuilderException, ClassNotFoundException {

		Style groupVariables = new Style("groupVariables");
		groupVariables.setFont(Font.ARIAL_MEDIUM_BOLD);
		groupVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
		groupVariables.isOverridesExistingStyle();
		groupVariables.setVerticalAlign(VerticalAlign.MIDDLE);
		groupVariables.getPaddingBottom();

		DynamicReportBuilder report = new DynamicReportBuilder();

		AbstractColumn colunaCode = createColumn("id", Long.class, "ID", 30, headerStyle, detailTextStyle);
		AbstractColumn colunaProduto = createColumn("produto", String.class, "Produto", 30, headerStyle, detailTextStyle);
		AbstractColumn colunaItem = createColumn("item", String.class, "Item", 30, headerStyle, detailNumStyle);
		AbstractColumn colunaValor = createColumn("valor", Float.class, "Valor", 30, headerStyle, detailNumStyle);
		report.addColumn(colunaCode).addColumn(colunaProduto).addColumn(colunaItem).addColumn(colunaValor);
		
		report.addGlobalFooterVariable(colunaValor, DJCalculation.SUM,groupVariables).setGrandTotalLegend("Total:");
		//report.addGlobalFooterVariable(colunaCode, DJCalculation.SUM,groupVariables);
		
		StyleBuilder titleStyle = new StyleBuilder(true);
		titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		titleStyle.setFont(new Font(20, null, true));
		// you can also specify a font from the classpath, eg:
		// titleStyle.setFont(new Font(20, "/fonts/someFont.ttf", true));

		StyleBuilder subTitleStyle = new StyleBuilder(true);
		subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		subTitleStyle.setFont(new Font(Font.MEDIUM, null, true));
		
		report.setTitle("Venda Report");
		report.setTitleStyle(titleStyle.build());
		report.setSubtitle("Lista de Venda");
		report.setSubtitleStyle(subTitleStyle.build());
		report.setUseFullPageWidth(true);
		report.addAutoText(AutoText.AUTOTEXT_PAGE_X_SLASH_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT);
		return report.build();
		
	}
	

	

}
