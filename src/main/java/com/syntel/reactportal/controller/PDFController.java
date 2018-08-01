package com.syntel.reactportal.controller;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.utils.PdfMerger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RequestMapping("/pdf")
@RestController
public class PDFController {

    @CrossOrigin
    @PostMapping("/read")
    public void readPdfFiles(@RequestParam("files") MultipartFile[] pdfFiles) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(new PdfReader(pdfFiles[0].getInputStream()));
        System.out.println(PdfTextExtractor.getTextFromPage(pdfDocument.getFirstPage()));
    }

    @CrossOrigin
    @PostMapping("/merge")
    public void mergePdfFiles(@RequestParam("files") MultipartFile[] pdfFiles, HttpServletResponse response) throws IOException {
        String dest = "demo";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(baos));

        PdfMerger pdfMerger = new PdfMerger(pdfDocument);
        for (MultipartFile file : pdfFiles) {
            PdfDocument fileDoc = new PdfDocument(new PdfReader(file.getInputStream()));
            pdfMerger.merge(fileDoc, 1, fileDoc.getNumberOfPages());
        }
        pdfMerger.close();
        baos.writeTo(response.getOutputStream());
    }
}
