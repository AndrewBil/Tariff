package com.my.parsers;


import com.my.operator.Operator;
import com.my.tariff.Tariff;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 10.05.2016.
 */
public class DOMParser {

    private Tariff tariff;

    public List<Tariff> parse(String fileXML) throws ParserConfigurationException, IOException, SAXException {
        List<Tariff> tariffs = new ArrayList<>();
        Tariff tariff = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fileXML));
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("tariff");

        for (int i = 0; i < nList.getLength(); i++) {
            Element tariffElem = (Element) nList.item(i);
            tariff = new Tariff();
            tariff.setName(tariffElem.getElementsByTagName("name").item(0).getTextContent());
            tariff.setPayroll(Integer.parseInt(tariffElem.getElementsByTagName("payroll").item(0).getTextContent()));
            tariff.setStartPrice(Integer.parseInt(tariffElem.getElementsByTagName("startPrice").item(0).getTextContent()));
            tariff.setCallPrices(parseCallPrices(tariffElem.getElementsByTagName("callPrices")));
            tariff.setOperator(parseOperator(tariffElem.getElementsByTagName("operator")));
            tariff.setSmsPrices(parseSMSPrices((tariffElem.getElementsByTagName("smsPrices"))));
            tariff.setParameters(parseParameters((tariffElem.getElementsByTagName("parameters"))));
            tariffs.add(tariff);
        }

        return tariffs;
    }

    private Tariff.CallPrices parseCallPrices(NodeList callPricesNodeList) {
        Element callPricesEL = (Element) callPricesNodeList.item(0);
        Tariff.CallPrices callPrices = new Tariff.CallPrices();
        callPrices.setToLandlineNumbers(Integer.parseInt(callPricesEL.getElementsByTagName("toLandlineNumbers").item(0).getTextContent()));
        callPrices.setToOtherNetworks(Integer.parseInt(callPricesEL.getElementsByTagName("toOtherNetworks").item(0).getTextContent()));
        callPrices.setWithinNetwork(Integer.parseInt(callPricesEL.getElementsByTagName("withinNetwork").item(0).getTextContent()));

        return callPrices;
    }

    private Tariff.SMSPrices parseSMSPrices(NodeList smsPricesNodeList) {
        Element smsPricesEL = (Element) smsPricesNodeList.item(0);
        Tariff.SMSPrices smsPrices = new Tariff.SMSPrices();
        smsPrices.setCountSMS(Integer.parseInt(smsPricesEL.getElementsByTagName("countSMS").item(0).getTextContent()));
        smsPrices.setSmsPrice(Integer.parseInt(smsPricesEL.getElementsByTagName("smsPrice").item(0).getTextContent()));

        return smsPrices;
    }

    private Operator parseOperator(NodeList operatorNodeList) {
        Element operatorEL = (Element) operatorNodeList.item(0);
        Operator operator = new Operator();
        operator.setOperatorName(operatorEL.getElementsByTagName("operatorName").item(0).getTextContent());

        return operator;
    }

    private Tariff.Parameters parseParameters(NodeList parametersNodeList) {
        Element parametersEL = (Element) parametersNodeList.item(0);
        Tariff.Parameters parameters = new Tariff.Parameters();
        parameters.setFavoriteNumber(Integer.parseInt(parametersEL.getElementsByTagName("favoriteNumber").item(0).getTextContent()));
        parameters.setTarificate(Tariff.Parameters.Tarification.valueOf(parametersEL.getElementsByTagName("tarificate").item(0).getTextContent()));

        return parameters;
    }
}


