package com.my.parsers;

import com.my.operator.Operator;
import com.my.tariff.Tariff;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 11.05.2016.
 */
public class SAXParser {

    private List<Tariff> tariffs;
    Tariff.CallPrices callPrices;
    Tariff.SMSPrices smsPrices;
    Tariff.Parameters parameters;
    Operator operator;
    private Tariff currentTariff;

    private int current;

    private class SAXHandler extends DefaultHandler {


        @Override
        public void startDocument() throws SAXException {
            tariffs = new ArrayList<>();

        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            switch (qName) {
                case "tariffs":
                    current = 1;
                    break;
                case "tariff":
                    current = 2;
                    currentTariff = new Tariff();
                    break;
                case "name":
                    current = 3;
                    break;
                case "operator":
                    operator = new Operator();
                    current = 4;
                    break;
                case "operatorName":
                    current = 41;
                    break;
                case "payroll":
                    current = 5;
                    break;
                case "startPrice":
                    current = 6;
                    break;
                case "callPrices":
                    current = 7;
                    callPrices = new Tariff.CallPrices();
                    break;
                case "toLandlineNumbers":
                    current = 8;
                    break;
                case "toOtherNetworks":
                    current = 9;
                    break;
                case "withinNetwork":
                    current = 10;
                    break;
                case "smsPrices":
                    current = 11;
                    smsPrices = new Tariff.SMSPrices();
                    break;
                case "countSMS":
                    current = 12;
                    break;
                case "smsPrice":
                    current = 13;
                    break;
                case "parameters":
                    current = 14;
                    parameters = new Tariff.Parameters();
                    break;
                case "favoriteNumber":
                    current = 15;
                    break;
                case "tarificate":
                    current = 16;
                    break;
            }
        }

        @Override
        public void characters(char[] buf, int start, int length) throws SAXException {

            String text = new String(buf, start, length);

            switch (current) {
                case 3:
                    currentTariff.setName(text);
                    break;
                case 41:
                    operator.setOperatorName(text);
                    currentTariff.setOperator(operator);
                    break;
                case 5:
                    currentTariff.setPayroll(Integer.parseInt(text));
                    break;
                case 6:
                    currentTariff.setStartPrice(Integer.parseInt(text));
                    break;
                case 8:
                    callPrices.setToLandlineNumbers(Integer.parseInt(text));
                    break;
                case 9:
                    callPrices.setToOtherNetworks(Integer.parseInt(text));
                    break;
                case 10:
                    callPrices.setWithinNetwork(Integer.parseInt(text));
                    break;
                case 12:
                    smsPrices.setCountSMS(Integer.parseInt(text));

                    break;
                case 13:
                    smsPrices.setSmsPrice(Integer.parseInt(text));

                    break;
                case 15:
                    parameters.setFavoriteNumber(Integer.parseInt(text));
                    break;
                case 16:
                    parameters.setTarificate(Tariff.Parameters.Tarification.valueOf(text));

            }

        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equalsIgnoreCase("tariff")) {
                currentTariff.setOperator(operator);
                currentTariff.setCallPrices(callPrices);
                currentTariff.setSmsPrices(smsPrices);
                currentTariff.setParameters(parameters);
                tariffs.add(currentTariff);
            }

            current = 0;
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();

        }
    }

    public List<Tariff> parse(String fileXML) {

        SAXHandler handler = new SAXHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser parser = null;

        try {
            parser = factory.newSAXParser();
            parser.parse(new File(fileXML), handler);

        } catch (ParserConfigurationException ex) {
            ex.getMessage();
        } catch (SAXException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }

        return tariffs;
    }

}