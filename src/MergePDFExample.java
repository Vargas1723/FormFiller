
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
 
 
public class MergePDFExample
{
        public static void main(String[] args)
        {
            try
            {
                List<InputStream> list = new ArrayList<InputStream>();
 
                InputStream inputStreamOne = new FileInputStream(new File("RES/NHE MANTO. SCADA INVERSOR PPAL.pdf"));
                list.add(inputStreamOne);
                InputStream inputStreamTwo = new FileInputStream(new File("RES/NHE MANTO. SCADA MCAD 5115-5145.pdf"));
                list.add(inputStreamTwo);
 
                OutputStream outputStream = new FileOutputStream(new File("RES/merge1.pdf"));
                mergePdf(list, outputStream);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (DocumentException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        private static void mergePdf(List<InputStream> list, OutputStream outputStream) throws DocumentException, IOException
        {
            Document document = new Document();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);
            document.open();
            PdfContentByte pdfContentByte = pdfWriter.getDirectContent();
 
            for (InputStream inputStream : list)
            {
                PdfReader pdfReader = new PdfReader(inputStream);
                for (int i = 1; i <= pdfReader.getNumberOfPages(); i++)
                {
                    document.newPage();
                    PdfImportedPage page = pdfWriter.getImportedPage(pdfReader, i);
                    pdfContentByte.addTemplate(page, 0, 0);
                }
            }
 
            outputStream.flush();
            document.close();
            outputStream.close();
        }
}