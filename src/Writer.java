
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.forms.PdfAcroForm;

import java.io.File;

public class Writer {
    public String DEST;

    public String SRC;
    
    public Equipo equipo;
    
    public Writer(String archivo, Equipo equipo) throws Exception {
    	this.SRC=archivo;
    	String descripcion = equipo.descripcion.replace("/", "-");
    	this.DEST="RES/"+descripcion+".pdf";
    	this.equipo=equipo;
    	//File file = new File(DEST);
        manipulatePdf(DEST);
    }

  
   
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(dest));
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);

        // The second parameter sets how the field's value will be displayed in the resultant pdf.
        // If the second parameter is null, then actual value will be shown.
        form.getField("SUBESTACION").setValue(equipo.getSubestacion()).setFontSize(8);
        form.getField("UBICACION").setValue(equipo.getUbicacion().substring(0, 7)).setFontSize(8).setJustification(0);
        form.getField("MARCA").setValue(equipo.getMarca()).setFontSize(8);
        form.getField("MODELO").setValue(equipo.getModelo()).setFontSize(8);
        form.getField("SERIE").setValue(equipo.getSerie()).setFontSize(8);
        form.getField("EQUIPO").setValue(equipo.getEquipo()).setFontSize(8);
        form.getField("DESCRIPCION").setValue(equipo.getDescripcion().charAt(10) == ' '?equipo.getDescripcion().substring(11):equipo.getDescripcion().substring(10)).setFontSize(7);
        form.getField("ORDEN").setValue(equipo.getOrden()).setFontSize(8);

        pdfDoc.close();
    }
}
