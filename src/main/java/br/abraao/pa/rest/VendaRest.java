package br.abraao.pa.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.abraao.pa.domain.Venda;
import br.abraao.pa.report.VendaReport;
import br.abraao.pa.repository.VendaRepository;
import br.abraao.pa.service.ServiceReport;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("api/vendas")
public class VendaRest {

	
	@Autowired
	VendaRepository vendaRepository;
	
	@Autowired
	ServiceReport serviceReport;
	

	@GetMapping
	public List<Venda> findAll(){
		return vendaRepository.findAll();
	}
	
	
	@GetMapping(path = "/vendaReport.pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public HttpEntity<byte[]> getEmployeeReportPdf(final HttpServletResponse response) throws JRException, IOException, ClassNotFoundException {
		final VendaReport report = new VendaReport(vendaRepository.findAll());
		final byte[] data = serviceReport.getReportPdf(report.getReport());

		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_PDF);
		header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=vendaReport.pdf");
		header.setContentLength(data.length);

		return new HttpEntity<byte[]>(data, header);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Venda insert( @RequestBody Venda venda) {
		return vendaRepository.save(venda);
	}

	
	
	
}
