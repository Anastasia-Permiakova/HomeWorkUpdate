package org.project.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * třida data transfer object, chrani v sebe data
 */

public class Model implements Serializable {

    private String city;

    private LocalDateTime localDateTime;

    private Double temp;

    private Double feels_like;

    public Double getFeels_like() {
        return feels_like;
    }


    public void setFeels_like(Double feels_like) {
        this.feels_like = feels_like;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    //vypis casu lastUpdate
    public String getLastUpdate(){
        String localDateTime = getLocalDateTime().toString();
        String[] ts = localDateTime.split("T");
        String[] date = ts[0].split("-");
        String[] time = ts[1].split(":");
        return "Lastupdate: "+date[2]+" "+date[1]+" "+date[0]+" v "+time[0]+" hodin "+time[1]+" minut "+time[2]+" sec";
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
