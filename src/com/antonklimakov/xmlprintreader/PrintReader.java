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
import java.util.HashMap;
import java.util.Map;

public class PrintReader {

    public static void main(String[] args) {
        String fileName = args[0];
        Map printMap = parseXML(fileName);
        for(Object print : printMap.values()){
            System.out.println(print.toString());
        }
    }

    private static Map<String, Print> parseXML(String fileName) {
        Map<String, Print> printMap = new HashMap<>();
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
                    } else if (startElement.getName().getLocalPart().equals("Amount")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        if (!xmlEvent.isEndElement()) {
                            if (xmlEvent.asCharacters().getData() != null)
                                print.setAmount(Integer.parseInt(xmlEvent.asCharacters().getData()));
                        }
                    }
                }
                //End of Print element, should be added to hashmap
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Print")) {

                        if(printMap.get(print.getDeviceAddress()) == null)
                            printMap.put(print.getDeviceAddress(), print);
                        else {
                            Print previous = printMap.get(print.getDeviceAddress());
                            int printPreviousAmount = previous.getAmount();
                            print.setAmount(print.getAmount() + printPreviousAmount);
                            printMap.put(print.getDeviceAddress(), print);
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
            e.printStackTrace();
        } catch (XMLStreamException e) {
            System.out.println("Something is wrong with xml file (maybe encoding)");
            e.printStackTrace();
        }

        return printMap;
    }
}
