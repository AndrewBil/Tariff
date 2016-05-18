package com.my.operator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by andrew on 10.05.2016.
 */

public class Operator {

    private String operatorName;

    public Operator() {
    }

    public String getOperatorName() {

        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Operator(String operatorName) {

        this.operatorName = operatorName;
    }
}
