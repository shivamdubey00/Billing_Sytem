package com.example.scms.Whatsaap;





import com.lowagie.text.Document;
import com.lowagie.text.PageSize;


import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.stereotype.Service;

import com.lowagie.text.Font;

import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

@Service
public class PdfService {
    public static class PdfResult {
        public final String fileName;
        public final String absolutePath;
        public PdfResult(String fileName, String absolutePath) {
            this.fileName = fileName; this.absolutePath = absolutePath;
        }
    }

    public PdfResult generateInvoice(InvoiceRequest req, File outputDir) throws Exception {
        if (!outputDir.exists()) outputDir.mkdirs();
        String fileName = "invoice-" + UUID.randomUUID() + ".pdf";
        File out = new File(outputDir, fileName);

        Document doc = new Document(PageSize.A4, 36, 36, 36, 36);
        PdfWriter.getInstance(doc, new FileOutputStream(out));
        doc.open();

        NumberFormat inr = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

        Font h1 = new Font(Font.HELVETICA, 18, Font.BOLD);
        Font h2 = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font normal = new Font(Font.HELVETICA, 11);

        Paragraph title = new Paragraph(req.getStoreName(), h1);
        title.setAlignment(Element.ALIGN_CENTER);
        doc.add(title);
        doc.add(new Paragraph("Invoice for: " + req.getCustomerName(), h2));
        doc.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{46, 12, 20, 22});

        addHeader(table, "Material");
        addHeader(table, "Qty");
        addHeader(table, "Unit Price");
        addHeader(table, "Line Total");

        double subtotal = 0;
        for (LineItem li : req.getItems()) {
            double line = li.getQuantity() * li.getUnitPrice();
            subtotal += line;
            addCell(table, li.getMaterial(), normal);
            addCell(table, String.valueOf(li.getQuantity()), normal, Element.ALIGN_RIGHT);
            addCell(table, inr.format(li.getUnitPrice()), normal, Element.ALIGN_RIGHT);
            addCell(table, inr.format(line), normal, Element.ALIGN_RIGHT);
        }
        doc.add(table);

        double tax = subtotal * (req.getTaxRatePercent() / 100.0);
        double total = subtotal + tax;

        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Subtotal: " + inr.format(subtotal), h2));
        doc.add(new Paragraph("Tax (" + req.getTaxRatePercent() + "%): " + inr.format(tax), h2));
        doc.add(new Paragraph("Total: " + inr.format(total), h2));

        if (req.getNotes() != null && !req.getNotes().isBlank()) {
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Notes: " + req.getNotes(), normal));
        }

        doc.add(new Paragraph(" "));
        doc.add(new Paragraph("Thank you for shopping at " + req.getStoreName() + "!", normal));

        doc.close();


        return new PdfResult(fileName, out.getAbsolutePath());
    }

    private static void addHeader(PdfPTable t, String s) {
        PdfPCell c = new PdfPCell(new Phrase(s, new Font(Font.HELVETICA, 11, Font.BOLD)));
        t.addCell(c);
    }
    private static void addCell(PdfPTable t, String s, Font f) { addCell(t, s, f, Element.ALIGN_LEFT); }
    private static void addCell(PdfPTable t, String s, Font f, int align) {
        PdfPCell c = new PdfPCell(new Phrase(s, f));
        c.setHorizontalAlignment(align);
        t.addCell(c);
    }
}

