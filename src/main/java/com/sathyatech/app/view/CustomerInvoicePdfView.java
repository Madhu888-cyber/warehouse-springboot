package com.sathyatech.app.view;

import java.awt.Color;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sathyatech.app.model.SaleOrder;
import com.sathyatech.app.model.SaleOrderDetails;

/**
 * @author Pratik
 *
 */
public class CustomerInvoicePdfView extends AbstractPdfView implements Serializable{


	private static final long serialVersionUID = 1L;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment;filename=custinvoice.pdf");

		SaleOrder saleOrder=(SaleOrder) model.get("saleOrder");
		
		if(saleOrder != null){

			List<SaleOrderDetails> saleDetails=saleOrder.getSaleDetails();

			double finalCost=0.0;

			for(SaleOrderDetails saleDetail:saleDetails){

				finalCost+=saleDetail.getItemDetails().getItemBaseCost() * saleDetail.getItemsQty();
			}

			doc.add(new Paragraph("CUSTOMER INVOICE CODE:CUST-"+saleOrder.getOrderCode()));

			// define font for table header row

			Font fontHeading = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			fontHeading.setColor(Color.WHITE);

			/*Font font = FontFactory.getFont(FontFactory.HELVETICA);
		fontHeading.setColor(Color.WHITE);*/

			// define table header cell
			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(Color.RED);
			cell.setPadding(5);


			PdfPTable heading=new PdfPTable(4);
			heading.setWidthPercentage(100.0f);
			heading.setWidths(new float[]{2.5f,2.5f,2.5f,2.5f});
			heading.setSpacingBefore(10);

			cell.setPhrase(new Phrase("Customer Code", fontHeading));
			heading.addCell(cell);
			heading.addCell(saleOrder.getSaleCustomers().getWhUserCode());

			cell.setPhrase(new Phrase("Order Code", fontHeading));
			heading.addCell(cell);
			heading.addCell(saleOrder.getOrderCode());

			cell.setPhrase(new Phrase("Final Cost", fontHeading));
			heading.addCell(cell);
			heading.addCell(new BigDecimal(finalCost).setScale(3, RoundingMode.CEILING).toString());

			cell.setPhrase(new Phrase("Shipment Type", fontHeading));
			heading.addCell(cell);
			heading.addCell(saleOrder.getSaleShipmentMode().getShipmentMode());

			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100.0f);
			table.setWidths(new float[] {1.0f, 3.0f, 2.0f, 2.0f, 2.0f});
			table.setSpacingBefore(10);


			// write table header
			cell.setPhrase(new Phrase("Sl No", fontHeading));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Item", fontHeading));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Cost", fontHeading));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Quantity", fontHeading));
			table.addCell(cell);

			cell.setPhrase(new Phrase("Total", fontHeading));
			table.addCell(cell);

			// write table row data
			for (SaleOrderDetails saleDetail : saleDetails) {
				table.addCell(String.valueOf(saleDetail.getSlno()));
				table.addCell(saleDetail.getItemDetails().getItemCode());
				table.addCell(String.valueOf(saleDetail.getItemDetails().getItemBaseCost()));
				table.addCell(String.valueOf(saleDetail.getItemsQty()));
				table.addCell(String.valueOf(saleDetail.getItemDetails().getItemBaseCost() * saleDetail.getItemsQty()));
			}
			doc.add(heading);
			doc.add(table);
			doc.add(new Paragraph("Generated On:"+new Date()));
		}
	}
}

