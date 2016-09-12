package com.hood.common.utils;


import org.springframework.security.crypto.codec.Base64;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

/**
 * @author taoranxue on 9/11/16 5:34 PM.
 */
public class ImageUtils {
    public static void convertBase64Content(String content) throws Exception {
        content = content.replace("<br>","<br/>");
        content = content.replace("px;\">","px;\"/>");
        InputStream contentSbIs = new ByteArrayInputStream(content.getBytes());
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(false);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document contentDocument = documentBuilder.parse(contentSbIs);
        System.out.println(contentDocument.getElementsByTagName("img").item(0).getAttributes().getNamedItem("src").getTextContent());

    }

    private void base64Decoder(String imageCode) throws IOException {
        byte[] data = Base64.decode(imageCode.getBytes());
        OutputStream stream = new FileOutputStream("~/decode/abc.png");
        stream.write(data);
    }
}
