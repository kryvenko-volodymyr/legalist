package ua.legalist.service.publish;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.docx4j.XmlUtils;
import org.docx4j.convert.in.xhtml.DivToSdt;
import org.docx4j.convert.in.xhtml.FormattingOption;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

/**
 * sample:
 * https://github.com/plutext/docx4j-ImportXHTML/blob/master/src/samples/java/org/docx4j/samples/XhtmlToDocxAndBack.java
 */
public class XhtmlToDocx {

    public static void main(String[] args) throws Exception {

        String path = "D:/Google Drive/legalist/publish/template.html";
        Charset encoding = Charset.forName("UTF-8");
        String html = readFile(path, encoding);

        // To docx, with content controls
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();

        XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
        XHTMLImporter.setRunFormatting(FormattingOption.IGNORE_CLASS);
        XHTMLImporter.setDivHandler(new DivToSdt());

        wordMLPackage.getMainDocumentPart().getContent().addAll(
                XHTMLImporter.convert(html, null));

        System.out.println(XmlUtils.marshaltoString(wordMLPackage
                .getMainDocumentPart().getJaxbElement(), true, true));

        wordMLPackage.save(new java.io.File("D:\\Google Drive\\legalist\\publish\\final_document.docx"));
    }

    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
