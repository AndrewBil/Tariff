package com.my.tariff;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.my.operator.Operator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by andrew on 10.05.2016.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Tariff")
public class Tariff {

    private String name;
    Operator operator;
    private Integer payroll;
    private Integer startPrice;
    public CallPrices callPrices;
    public SMSPrices smsPrices;
    public Parameters parameters;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonInString = gson.toJson(this);
        return ("Tariff: " + jsonInString);
    }
    public void setSmsPrices(SMSPrices smsPrices) {
        this.smsPrices = smsPrices;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public Operator getOperator() {
        return operator;
    }

    public String getName() {
        return name;
    }

    public Integer getStartPrice() {
        return startPrice;
    }

    public Integer getPayroll() {
        return payroll;
    }


    public Tariff() {
    }

    public Tariff(String name, Operator operator, Integer startPrice, Integer
            payroll, CallPrices callPrices, SMSPrices smsPrices, Parameters parameters) {
        this.name = name;
        this.operator = operator;
        this.startPrice = startPrice;
        this.payroll = payroll;
        this.callPrices = callPrices;
        this.smsPrices = smsPrices;
        this.parameters = parameters;

    }


    public void setName(String name) {
        this.name = name;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }

    public void setCallPrices(CallPrices callPrices) {
        this.callPrices = callPrices;
    }

    public void setPayroll(Integer payroll) {
        this.payroll = payroll;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }


    public static class CallPrices {
        private Integer withinNetwork;
        private Integer toOtherNetworks;
        private Integer toLandlineNumbers;


        public CallPrices(CallPrices callPrices) {
        }

        public CallPrices(Integer withinNetwork, Integer toOtherNetworks, Integer
                toLandlineNumbers) {
            this.withinNetwork = withinNetwork;
            this.toOtherNetworks = toOtherNetworks;
            this.toLandlineNumbers = toLandlineNumbers;
        }

        public CallPrices() {
        }

        public Integer getToLandlineNumbers() {
            return toLandlineNumbers;
        }


        public void setToLandlineNumbers(Integer toLandlineNumbers) {
            this.toLandlineNumbers = toLandlineNumbers;
        }

        public Integer getWithinNetwork() {
            return withinNetwork;
        }


        public void setWithinNetwork(Integer withinNetwork) {
            this.withinNetwork = withinNetwork;
        }

        public Integer getToOtherNetworks() {
            return toOtherNetworks;
        }


        public void setToOtherNetworks(Integer toOtherNetworks) {
            this.toOtherNetworks = toOtherNetworks;
        }

    }


    public static class SMSPrices {
        public SMSPrices(Integer smsPrice, Integer countSMS) {
            this.smsPrice = smsPrice;
            this.countSMS = countSMS;
        }

        public Integer getCountSMS() {

            return countSMS;
        }

        public void setCountSMS(Integer countSMS) {
            this.countSMS = countSMS;
        }

        private Integer smsPrice;
        private Integer countSMS;

        public SMSPrices() {
        }

        public Integer getSmsPrice() {
            return smsPrice;
        }


        public void setSmsPrice(Integer smsPrice) {
            this.smsPrice = smsPrice;
        }


    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "parameters")
    public static class Parameters {
        private Integer favoriteNumber;
        Tarification tarificate;

        public Parameters() {
        }

        public Parameters(int favoriteNumber, Tarification tarificate) {
            this.favoriteNumber = favoriteNumber;
            this.tarificate = tarificate;
        }

        public int getFavoriteNumber() {
            return favoriteNumber;
        }

        public void setFavoriteNumber(Integer favoriteNumber) {
            this.favoriteNumber = favoriteNumber;
        }

        public Tarification getTarificate() {
            return tarificate;
        }

        public void setTarificate(Tarification tarificate) {
            this.tarificate = tarificate;
        }


        public enum Tarification {
            EVERY15SECONDS,
            EVERYMINUTE;

        }


    }
}



