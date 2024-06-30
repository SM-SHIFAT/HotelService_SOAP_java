package com.fm.lifevoy.v2.generatefeed.clients;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class XMLUtils {
    public static Document getParsedOutput(String xmlInput, String link) throws IOException, ParserConfigurationException, SAXException {
        URL url = new URL(link);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

        byte[] buffer = xmlInput.getBytes();
        httpConn.setRequestProperty("Content-Length", String.valueOf(buffer.length));
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        httpConn.setRequestProperty("SOAPAction", "");
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);

        OutputStream request = httpConn.getOutputStream();
        request.write(buffer);
        request.close();

        BufferedReader response = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));

        String line;
        String outputString="";
        while ((line = response.readLine()) != null)
            outputString += line;

        InputSource inputSource = new InputSource(new StringReader(outputString));
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
    }
}
