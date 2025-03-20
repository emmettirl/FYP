package note.reader.view.reader.components.document.format

import org.docx4j.Docx4J
import org.docx4j.openpackaging.packages.WordprocessingMLPackage
import java.io.File
import java.io.FileOutputStream

fun convertDocxToPdf(docxPath: String, pdfPath: String) {
    val wordMLPackage: WordprocessingMLPackage = WordprocessingMLPackage.load(File(docxPath))
    FileOutputStream(pdfPath).use { outputStream ->
        Docx4J.toPDF(wordMLPackage, outputStream)
    }
}