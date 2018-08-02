package com.syntel.reactportal.controller;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.utils.PdfMerger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Types;

@RequestMapping("/pdf")
@RestController
public class PDFController {
    @Autowired
    JdbcTemplate jdbcTemplate;

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

    @CrossOrigin
    @PostMapping("/upload")
    public void uploadPdfFiles(@RequestParam("files") MultipartFile[] pdfFiles, HttpServletResponse response) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfDocument pdfDocument = new PdfDocument(new PdfReader(pdfFiles[0].getInputStream()), new PdfWriter(baos));
        LobHandler lobHandler = new DefaultLobHandler();
        byte[] bytes = baos.toByteArray();
        String name = pdfDocument.getDocumentInfo().getTitle();
        String description = pdfDocument.getDocumentInfo().getAuthor();
        jdbcTemplate.update(
                "INSERT INTO PORTAL.PDF (name, content, description) VALUES (?, ?, ?)",
                new Object[] {
                        name,
                        new SqlLobValue(bytes, lobHandler),
                        new SqlLobValue(description, lobHandler)
                },
                new int[] {Types.VARCHAR, Types.BLOB, Types.CLOB});
    }
}
