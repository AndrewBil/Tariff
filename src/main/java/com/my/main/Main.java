package com.my.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.my.operator.Operator;
import com.my.parsers.DOMParser;
import com.my.parsers.SAXParser;
import com.my.tariff.Tariff;
import com.my.tariff.Tariffs;
import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 10.05.2016.
 */
public class Main {
    static final String fileXML = "tariff.xml";
    static final String fileXSD = "tariff.xsd";
    static Tariffs tariffs = new Tariffs();

    private static void marshalingExample() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Tariffs.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //Marshal the employees list in console
        jaxbMarshaller.marshal(tariffs, System.out);

        //Marshal the employees list in file
        jaxbMarshaller.marshal(tariffs, new File("tariffs.xml"));
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        tariffs.setTariffs(new ArrayList<Tariff>());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Tariff tariff = new Tariff("Talk", new Operator("Kyivstar"), 25,50,new Tariff.CallPrices(0,20,25),new Tariff.SMSPrices(20,100),new Tariff.Parameters(1,Tariff.Parameters.Tarification.EVERYMINUTE));

        tariffs.add(tariff);
            String jsonInString = gson.toJson(tariff);
        try {
            FileUtils.writeStringToFile(new File("test.json"), jsonInString);
        } catch (IOException e) {
            e.printStackTrace();
        }
              try {
            marshalingExample();
            System.out.println("tariffs.xml validates against tariffs.xsd? "+validateXMLSchema(fileXSD, fileXSD));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        DOMParser parser = new DOMParser();
        List<Tariff> tariffs = parser.parse(fileXML);
        for (Tariff tar : tariffs) {
                   System.out.println(tar.toString());
        }

        SAXParser parser1=new SAXParser();
        List<Tariff> tariffs1=parser1.parse(fileXML);
         for (Tariff tar:tariffs1){
                       System.out.println(tar.toString());
        }
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath){

        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
    }

