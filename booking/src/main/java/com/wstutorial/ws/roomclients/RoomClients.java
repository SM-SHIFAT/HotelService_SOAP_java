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
    public static List<RoomType> getAllRooms() throws IOException, ParserConfigurationException, SAXException {
        String xmlInput =
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:get=\"http://hotelservice.com/getallrooms\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <get:GetAllRoomsRequest/>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>";

        Document document = getParsedOutput(xmlInput, "http://localhost:8082/wsdlfirst/roomService");

        NodeList nodeList = document.getElementsByTagName("ns3:rooms");
        List<RoomType> rooms = new ArrayList<RoomType>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList children = nodeList.item(i).getChildNodes();
            RoomType room = new RoomType();
            for (int j = 0; j < children.getLength(); j++) {
                String textContent = children.item(j).getTextContent();
                String nodeName = children.item(j).getNodeName();
                if (nodeName.equals("ns2:roomNumber")) {
                    room.setRoomNumber(Integer.parseInt(textContent));
                } else if (nodeName.equals("ns2:roomType")) {
                    room.setRoomType(textContent);
                } else if (nodeName.equals("ns2:roomCost")) {
                    room.setRoomCost(Integer.parseInt(textContent));
                } else if (nodeName.equals("ns2:isAvailable")) {
                    room.setIsAvailable(Boolean.parseBoolean(textContent));
                }

            }
            rooms.add(room);
        }
        return rooms;
    }

    public static void bookRoomAfterPay(int roomNumber) throws IOException, ParserConfigurationException, SAXException {
        String xmlInput =
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:book=\"http://hotelservice.com/bookroom\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <book:BookRoomRequest>\n" +
                        "         <book:roomNumber>"+roomNumber+"</book:roomNumber>\n" +
                        "      </book:BookRoomRequest>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>";

        Document document = getParsedOutput(xmlInput,"http://localhost:8082/wsdlfirst/roomService");
    }

    public static void checkoutRoom(int roomNumber) throws IOException, ParserConfigurationException, SAXException {
        String xmlInput =
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:chec=\"http://hotelservice.com/checkoutroom\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <chec:CheckoutRoomRequest>\n" +
                        "         <chec:roomNumber>"+roomNumber+"</chec:roomNumber>\n" +
                        "      </chec:CheckoutRoomRequest>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>";

        Document document = getParsedOutput(xmlInput,"http://localhost:8082/wsdlfirst/roomService");
    }

    public static boolean paymentRequest(String userId, int roomNumber, int amount, String date) throws IOException, ParserConfigurationException, SAXException {
        String xmlInput =
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:pay=\"http://hotelservice.com/payment\">\n" +
                        "   <soapenv:Header/>\n" +
                        "   <soapenv:Body>\n" +
                        "      <pay:PaymentRequest>\n" +
                        "         <pay:userId>" + userId + "</pay:userId>\n" +
                        "         <pay:roomNumber>" + roomNumber + "</pay:roomNumber>\n" +
                        "         <pay:amount>" + amount + "</pay:amount>\n" +
                        "         <pay:date>" + date + "</pay:date>\n" +
                        "         <pay:isPaid>" + true + "</pay:isPaid>\n" +
                        "      </pay:PaymentRequest>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>";

        Document document = getParsedOutput(xmlInput, "http://localhost:8084/wsdlfirst/paymentService");

        NodeList nodeList = document.getElementsByTagName("ns2:success");
        if (nodeList.getLength() > 0) {
            String textContent = nodeList.item(0).getTextContent();
            return Boolean.parseBoolean(textContent);
        }
        return false;
    }

}
