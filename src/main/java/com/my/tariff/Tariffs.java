package com.my.tariff;

import com.my.tariff.Tariff;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 10.05.2016.
 */
@XmlRootElement()
@XmlAccessorType (XmlAccessType.FIELD)
public class Tariffs
{
    @XmlElement(name = "tariff")
    private List<Tariff> tariffs = null;

    public List<Tariff> getTariffs() {
        return tariffs;
    }


    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    public void add(Tariff tariff )
    {
        if( this.tariffs == null )
        {
            this.tariffs = new ArrayList<Tariff>();
        }
        this.tariffs.add( tariff );

    }
}
