package cschmidt;

import static org.junit.Assert.*;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitTest {
    
    @Test
    public void testDocumentWrite() throws Exception {
        WebClient browser = new WebClient(BrowserVersion.FIREFOX_17);
        HtmlPage page = browser.getPage(getClass().getResource("/with-document-write-script.html"));
        System.out.println(docToString(page));
        assertFalse("body text", page.getBody().getTextContent().contains("hope I am not in the body!"));
    }
    
    /**
     * Serializes a DOM document as a string.
     */
    private String docToString(Node doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();        
        Transformer t = transformerFactory.newTransformer();
        t.setOutputProperty(OutputKeys.METHOD, "html");
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter sw = new java.io.StringWriter();
        StreamResult result = new javax.xml.transform.stream.StreamResult(sw);
        DOMSource source = new DOMSource(doc);
        t.transform(source, result);
        return sw.toString();
    }
    
}
