package com.antonklimakov.xmlprintreader;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PrintReader {

    public static void main(String[] args) {
        String fileName = "jac1.xml";
        List<Print> printList = parseXML(fileName);
        for(Print print : printList){
            System.out.println(print.toString());
        }
    }

    private static List<Print> parseXML(String fileName) {
        List<Print> printList = new ArrayList<>();
        Print print = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("Print")) {
                        print = new Print();
                        Attribute deviceAddressAttr = startElement.getAttributeByName(new QName("deviceAddress"));
                        if (deviceAddressAttr != null)
                            print.setDeviceAddress(deviceAddressAttr.getValue());
                    } else if (startElement.getName().getLocalPart().equals("UserLogin")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        print.setUserLogin(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("Amount")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        print.setAmount(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Print"))
                        printList.add(print);
                }
            }

        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }

        return printList;
    }
}
