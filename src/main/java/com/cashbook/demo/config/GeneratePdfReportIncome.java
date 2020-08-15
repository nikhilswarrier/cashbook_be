package com.cashbook.demo.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.cashbook.demo.model.Income;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdfReportIncome {


    public static ByteArrayInputStream incomeReport(List<Income> incomes) {

        float totalAMount=0;
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(90);
            table.setWidths(new int[] { 1, 2, 3, 3, 2 });

            Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Id", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Date", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Particulars", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Voucher No.", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Amount", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (Income income : incomes) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(income.getId().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(income.getDate().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(income.getParticulars())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(income.getVrNumber())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(" ₹ " + String.valueOf(income.getAmount())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);

                totalAMount+=income.getAmount();
            }

             hcell = new PdfPCell(new Phrase());
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

              hcell = new PdfPCell(new Phrase("Total Amount", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(hcell);

             hcell = new PdfPCell(new Phrase());
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

             hcell = new PdfPCell(new Phrase());
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

             hcell = new PdfPCell(new Phrase(String.valueOf(totalAMount)));
            hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(hcell);


            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(GeneratePdfReportIncome.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}