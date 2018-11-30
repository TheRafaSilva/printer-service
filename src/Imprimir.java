

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.print.DocPrintJob;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

public class Imprimir {

  public static void main(String[] args) throws IOException, PrinterException {
    
	  String filename;
	  
	  filename = "/assets/test_page.pdf";
	  
	  String printerNameDesired = "PRINTER_NAME";
	  
	  javax.print.PrintService[] service = PrinterJob.lookupPrintServices();
	  DocPrintJob docPrintJob = null;
	  
	  int count = service.length;
	  
	  for (int i = 0; i < count; i++) {
		  
		  if (service[i].getName().contains(printerNameDesired)) {
			  docPrintJob = service[i].createPrintJob();
			  i = count;
		  }
	  }
	  
    try {
      PDDocument pdf = PDDocument.load(new File(filename));
      PrinterJob job = PrinterJob.getPrinterJob();
      job.setPrintService(docPrintJob.getPrintService());
      job.setJobName("job");
      job.setPageable(new PDFPageable(pdf));
      job.print();
            
      pdf.close();
      
      new File(filename).delete();
      System.exit(0);
      
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}