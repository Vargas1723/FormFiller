
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class MergePdf {

    public static void main(String[] args) {
        try {
            List<InputStream> pdfs = new ArrayList<InputStream>();

            pdfs.add(new FileInputStream("RES/NHE MANTO. SCADA INVERSOR PPAL.pdf"));
            pdfs.add(new FileInputStream("RES/NHE MANTO. SCADA MCAD 5115-5145.pdf"));           
            OutputStream output = new FileOutputStream("RES/merge1.pdf");
            MergePdf.concatPDFs(pdfs, output, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void concatPDFs(List<InputStream> streamOfPDFFiles,
            OutputStream outputStream, boolean paginate) {

        Document document = new Document();
        try {
            List<InputStream> pdfs = streamOfPDFFiles;
            List<PdfReader> readers = new ArrayList<PdfReader>();
            int totalPages = 0;
            Iterator<InputStream> iteratorPDFs = pdfs.iterator();

            // Create Readers for the pdfs.
            int i=1;
            while (iteratorPDFs.hasNext()) {
                InputStream pdf = iteratorPDFs.next();
                PdfReader pdfReader = new PdfReader(pdf);
                System.out.println("Page size is "+pdfReader.getPageSize(1));
                readers.add(pdfReader);
                totalPages += pdfReader.getNumberOfPages();
                i++;
            }
            // Create a writer for the outputstream
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            writer.setCompressionLevel(9);
            document.open();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
                    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            PdfContentByte cb = writer.getDirectContent(); // Holds the PDF data

            PdfImportedPage page;
            int currentPageNumber = 0;
            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = readers.iterator();

            // Loop through the PDF files and add to the output.
            while (iteratorPDFReader.hasNext()) {
                PdfReader pdfReader = iteratorPDFReader.next();

                // Create a new page in the target for each source page.
                System.out.println("No. of pages "+pdfReader.getNumberOfPages());
               i=0;
                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                    Rectangle r=pdfReader.getPageSize(pdfReader.getPageN(pageOfCurrentReaderPDF+1));
                    if(r.getWidth()==792.0 && r.getHeight()==612.0)
                        document.setPageSize(PageSize.A4.rotate());
                    else
                        document.setPageSize(PageSize.A4);
                    document.newPage();
                    pageOfCurrentReaderPDF++;
                    currentPageNumber++;
                    i++;

                    page = writer.getImportedPage(pdfReader,
                            pageOfCurrentReaderPDF);
                    System.out.println("Width is "+page.getWidth());
                    System.out.println("Height is "+page.getHeight());
                    cb.newlineText();
                    cb.addTemplate(page, 0, 0);

                    // Code for pagination.
                    if (paginate) {
                        cb.beginText();
                        cb.setFontAndSize(bf, 9);
                        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, ""
                                + currentPageNumber + " of " + totalPages, 520,
                                5, 0);
                        cb.endText();
                    }
                }
                pageOfCurrentReaderPDF = 0;
            }
            outputStream.flush();
            document.close();
            outputStream.close();
            System.out.println("Merging of Pdfs is done.......");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen())
                document.close();
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}