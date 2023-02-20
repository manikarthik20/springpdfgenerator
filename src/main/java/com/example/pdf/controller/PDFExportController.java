package com.example.pdf.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.pdf.service.PDFGeneratorService;
import com.lowagie.text.DocumentException;

@Controller
public class PDFExportController {

	private final PDFGeneratorService pdfGeneratorService;

	public PDFExportController(PDFGeneratorService pdfGeneratorService) {
		this.pdfGeneratorService = pdfGeneratorService;
	}

	@GetMapping("/pdf/generate")
	public void generatePDF(HttpServletResponse respone) throws IOException {
		respone.setContentType("application/pdf");
		DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime = dateFormate.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment: filename=pdf_"+ currentDateTime + ".pdf";
		respone.setHeader(headerKey, headerValue);
		
		this.pdfGeneratorService.export(respone);
	}

}
