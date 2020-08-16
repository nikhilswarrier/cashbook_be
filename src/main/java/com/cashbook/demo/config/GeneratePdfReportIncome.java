package com.cashbook.demo.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.cashbook.demo.model.IncomeDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdfReportIncome {

    public static ByteArrayInputStream incomeReport(List<IncomeDTO> incomes) {

        IncomeDTO iDto;
        float totalAMount, cumulative;
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(90);
            table.setWidths(new int[] { 1, 3, 3, 3, 2 });

            Font headFont = FontFactory.getFont(FontFactory.COURIER);

            PdfWriter writer = PdfWriter.getInstance(document, out);
            PdfHeader event = new PdfHeader();
            writer.setPageEvent(event);
            document.open();
            document.addTitle("Income Report");
            document.addAuthor("Income Report");

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

            for (IncomeDTO income : incomes) {

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
            }

            iDto = incomes.get(0);
            totalAMount = iDto.getAmount();
            cumulative = iDto.getCumulativeBalance();

            hcell = new PdfPCell(new Phrase("Total Amount", headFont));
            hcell.setColspan(4);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(String.valueOf(totalAMount)));
            hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Cumulative Balance", headFont));
            hcell.setColspan(4);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(String.valueOf(cumulative)));
            hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(hcell);

            document.add(table);

            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(GeneratePdfReportIncome.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public static class PdfHeader extends PdfPageEventHelper {

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            try {
                Rectangle pageSize = document.getPageSize();
                ColumnText.showTextAligned(writer.getDirectContent(),Element.TITLE, new Phrase("Income Report",FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE)),
                        pageSize.getLeft(275), pageSize.getTop(30), 0);

                 ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(),
                        pageSize.getLeft(275), pageSize.getTop(30), 0);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}