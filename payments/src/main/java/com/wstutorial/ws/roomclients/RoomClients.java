package com.wstutorial.ws.roomclients;

import com.wstutorial.ws.generated.room.RoomType;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.fm.lifevoy.v2.generatefeed.clients.XMLUtils.getParsedOutput;

public class RoomClients {

    public static boolean addLog(String logMessage) throws IOException, ParserConfigurationException, SAXException {
        String xmlInput =
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:add=\"http://hotelservice.com/addlog\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <add:AddLogRequest>\n" +
                        "         <add:logMessage>" + logMessage + "</add:logMessage>\n" +
                        "      </add:AddLogRequest>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>";

        Document document = getParsedOutput(xmlInput, "http://localhost:8085/wsdlfirst/utilityService");

        NodeList nodeList = document.getElementsByTagName("ns2:success");
        if (nodeList.getLength() > 0) {
            String textContent = nodeList.item(0).getTextContent();
            return Boolean.parseBoolean(textContent);
        }
        return false;
    }



}
