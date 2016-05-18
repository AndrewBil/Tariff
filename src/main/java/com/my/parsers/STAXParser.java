package com.my.parsers;

import com.my.operator.Operator;
import com.my.tariff.Tariff;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 12.05.2016.
 */
public class STAXParser {
    private Tariff tariff = new Tariff();

    private List<Tariff> tariffs;
    Operator operator;
    Tariff.CallPrices callPrices;
    Tariff.SMSPrices smsPrices;
    Tariff.Parameters parameters;
    private Tariff currentTariff;

    public List<Tariff> parse(String fileXML) {

        InputStream input = null;
        try {
            input = new FileInputStream(new File(fileXML));
        } catch (FileNotFoundException ex) {
            System.out.println("Problem with file" + ex.getMessage());
        }

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        try {
            reader = inputFactory.createXMLStreamReader(input);
        } catch (XMLStreamException ex) {
            System.out.println("Problem with creating XML stream reader" + ex.getMessage());
        }

        tariffs = new ArrayList<>();


        try {

            while (reader.hasNext()) {


                int eventType = reader.next();

                if (eventType == XMLEvent.START_ELEMENT) {

                    String currentElementName = reader.getLocalName();

                    switch (currentElementName) {
                        case "tariff":
                            currentTariff = new Tariff();
                            break;
                        case "name":
                            currentTariff.setName(reader.getElementText());
                            break;
                        case "operator":
                            operator = new Operator();
                            break;
                        case "operatorName":
                            operator.setOperatorName(reader.getElementText());
                            currentTariff.setOperator(operator);
                            break;
                        case "payroll":
                            currentTariff.setPayroll(Integer.parseInt(reader.getElementText()));
                            break;
                        case "startPrice":
                            currentTariff.setStartPrice(Integer.parseInt(reader.getElementText()));
                            break;
                        case "callPrices":
                            callPrices = new Tariff.CallPrices();
                            break;
                        case "toLandlineNumbers":
                            callPrices.setToLandlineNumbers(Integer.parseInt(reader.getElementText()));
                            break;
                        case "toOtherNetworks":
                            callPrices.setToOtherNetworks(Integer.parseInt(reader.getElementText()));
                            break;
                        case "withinNetwork":
                            callPrices.setWithinNetwork(Integer.parseInt(reader.getElementText()));
                            break;
                        case "smsPrices":
                            smsPrices = new Tariff.SMSPrices();
                            break;
                        case "countSMS":
                            smsPrices.setCountSMS(Integer.parseInt(reader.getElementText()));
                            break;
                        case "smsPrice":
                            smsPrices.setSmsPrice(Integer.parseInt(reader.getElementText()));
                            break;
                        case "parameters":
                            parameters = new Tariff.Parameters();
                            break;
                        case "favoriteNumber":
                            parameters.setFavoriteNumber(Integer.parseInt(reader.getElementText()));
                            break;
                        case "tarificate":
                            parameters.setTarificate(Tariff.Parameters.Tarification.valueOf(reader.getElementText()));
                            break;
                    }
                }
                }
            currentTariff.setOperator(operator);
            currentTariff.setCallPrices(callPrices);
            currentTariff.setSmsPrices(smsPrices);
            currentTariff.setParameters(parameters);
            tariffs.add(currentTariff);

            }catch(XMLStreamException ex){
                System.out.println("There is problem with xml" + ex.getMessage());
            }

            return tariffs;
        }

    }
