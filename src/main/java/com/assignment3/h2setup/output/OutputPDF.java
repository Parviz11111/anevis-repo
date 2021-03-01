package com.assignment3.h2setup.output;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class OutputPDF {

    public static void renderPDF(String filepath, String sourceFilename, String dataName) {

        Document document = new Document(PageSize.A3, 20, 20, 20, 20);

        try {
            FileOutputStream fos = new FileOutputStream(filepath + dataName);
            PdfWriter writer = PdfWriter.getInstance(document, fos);
            writer.open();
            document.open();
            document.add(Image.getInstance(filepath + sourceFilename));
            document.close();
            writer.close();

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
