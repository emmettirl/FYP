package note.reader.controller.conversion

import org.docx4j.Docx4J
import org.docx4j.openpackaging.packages.WordprocessingMLPackage
import java.io.File
import java.io.FileOutputStream

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory
import org.apache.poi.xslf.usermodel.XMLSlideShow
import java.awt.*
import java.awt.image.BufferedImage
import java.io.FileInputStream



object DocumentConverter {

    private val tempDir = File(System.getProperty("java.io.tmpdir"), "note_reader_temp").apply { mkdirs() }

    fun convertEpubToPdf(epubPath: String, pdfPath: String): String {
        val tempPdfPath = File(tempDir, pdfPath).absolutePath

        CalibreDriver.convertEpubToPdf(epubPath, tempPdfPath)
        return tempPdfPath
    }

    fun convertHtmlToPdf(htmlPath: String): String {

        val tempPdfPath = LibreOfficeDriver.convertHtmlToPdf(htmlPath, tempDir)

        return tempPdfPath.absolutePath
    }


    fun convertDoctoDocx(docPath: String, temp_dir: File = File(tempDir, "docx").apply { mkdirs() }): String {

        return LibreOfficeDriver.convertDocToDocx(docPath, temp_dir)
    }


    fun convertDocxToPdf(docxPath: String, pdfPath: String): String {
        val wordMLPackage: WordprocessingMLPackage = WordprocessingMLPackage.load(File(docxPath))
        val tempPdfPath = File(tempDir, pdfPath).absolutePath
        FileOutputStream(tempPdfPath).use { outputStream ->
            Docx4J.toPDF(wordMLPackage, outputStream)
        }
        return tempPdfPath
    }

    fun convertPptxToPdf(pptxPath: String, pdfPath: String): String {
        val pptFile = File(pptxPath)
        val tempPdfPath = File(tempDir, pdfPath).absolutePath

        FileInputStream(pptFile).use { fis ->
            val ppt = XMLSlideShow(fis)
            val pdfDoc = PDDocument()

            val slideSize = ppt.pageSize
            val slideWidth = slideSize.width.toFloat()
            val slideHeight = slideSize.height.toFloat()

            val slides = ppt.slides
            var index = 0

            while (index < slides.size) {
                val pdPage = PDPage()
                pdfDoc.addPage(pdPage)

                val pdfWidth = pdPage.mediaBox.width
                val pdfHeight = pdPage.mediaBox.height

                val scale = pdfWidth / slideWidth
                val newWidth = pdfWidth
                val newHeight = slideHeight * scale

                val halfPageHeight = pdfHeight / 2
                val offsetY1 = halfPageHeight + (halfPageHeight - newHeight) / 2
                val offsetY2 = (halfPageHeight - newHeight) / 2

                PDPageContentStream(pdfDoc, pdPage).use { contentStream ->
                    val img1 = renderSlideToImage(slides[index], slideSize)
                    val pdImage1 = LosslessFactory.createFromImage(pdfDoc, img1)
                    contentStream.drawImage(pdImage1, 0f, offsetY1, newWidth, newHeight)

                    if (index + 1 < slides.size) {
                        val img2 = renderSlideToImage(slides[index + 1], slideSize)
                        val pdImage2 = LosslessFactory.createFromImage(pdfDoc, img2)
                        contentStream.drawImage(pdImage2, 0f, offsetY2, newWidth, newHeight)
                    }
                }

                index += 2
            }

            pdfDoc.save(tempPdfPath)
            pdfDoc.close()
            println("Conversion Completed: $tempPdfPath")
        }
        return tempPdfPath
    }

    fun renderSlideToImage(slide: org.apache.poi.xslf.usermodel.XSLFSlide, slideSize: Dimension): BufferedImage {
        val img = BufferedImage(slideSize.width, slideSize.height, BufferedImage.TYPE_INT_RGB)
        val graphics = img.createGraphics()

        graphics.color = Color.WHITE
        graphics.fillRect(0, 0, slideSize.width, slideSize.height)
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC)

        slide.draw(graphics)
        graphics.dispose()

        return img
    }



}
