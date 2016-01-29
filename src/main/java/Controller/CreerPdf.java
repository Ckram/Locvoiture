package Controller;

import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import Model.Contrat;

public class CreerPdf {

	public static void creationPdf(Contrat c)
	{
		String outputFileName = c.getIdContrat()+".pdf";
		int line = 0;
		PDDocument document = new PDDocument();
        PDPage page1 = new PDPage(PDPage.PAGE_SIZE_A4);
            // PDPage.PAGE_SIZE_LETTER is also possible
        PDRectangle rect = page1.getMediaBox();
            // rect can be used to get the page width and height
        document.addPage(page1);
 
        // Create a new font object selecting one of the PDF base fonts
        PDFont fontPlain = PDType1Font.HELVETICA;
        PDFont fontBold = PDType1Font.HELVETICA_BOLD;
        PDFont fontItalic = PDType1Font.HELVETICA_OBLIQUE;
        PDFont fontMono = PDType1Font.COURIER;
        try {
			PDPageContentStream cos = new PDPageContentStream(document, page1);
			
			 cos.beginText();
			 	cos.setFont(fontBold, 42);
			 	cos.moveTextPositionByAmount(rect.getWidth()/2-100 , rect.getHeight() - 100*(++line));
			 	cos.drawString("Facture");
			 	line +=3;
			 	cos.endText();
			 	cos.beginText();
		        cos.setFont(fontPlain, 12);
		        cos.moveTextPositionByAmount(50, rect.getHeight() - 25*(++line));
		        cos.drawString("Client : "+c.getClient());
		        cos.endText();
		        cos.beginText();
		        cos.moveTextPositionByAmount(50, rect.getHeight() - 25*(++line));
		        cos.drawString("Agent : "+c.getAgent().getNom()+" "+c.getAgent().getPrenom());
		        cos.endText();
		        cos.beginText();
		        cos.moveTextPositionByAmount(50, rect.getHeight() - 25*(++line));
		        cos.drawString("Voiture : Plaque : \""+c.getVoiture().getPlaqueImm()+"\" Marque : "+c.getVoiture().getMarque());		      	        
		        cos.endText();
		        cos.beginText();
		        cos.moveTextPositionByAmount(50, rect.getHeight() - 25*(++line));
		        cos.drawString("Nombre Km roulé :  "+(c.getKmFin()-c.getKmDebut())+" Prix Journalier : "+c.getVoiture().getPrixJour());		      	        
		        cos.endText();
		        cos.beginText();
		        cos.moveTextPositionByAmount(50, rect.getHeight() - 25*(++line));
		        cos.setFont(fontBold, 12);
		        DecimalFormat df = new DecimalFormat();
		        df.setMaximumFractionDigits(2);
		        cos.drawString("Prix Total :  "+df.format(c.getPrixFinale()));		      	        
		        cos.endText();
		        
		 
		        
		 
		        // Make sure that the content stream is closed:
		        cos.close();
		        document.save(outputFileName);
		        document.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (COSVisitorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
        
	}
	
}
