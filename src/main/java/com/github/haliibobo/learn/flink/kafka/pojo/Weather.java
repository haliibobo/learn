package com.github.haliibobo.learn.flink.kafka.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.haliibobo.learn.tools.JacksonUtil;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * cat Weather| uniq | sort | awk '{print "private String " $1 " ;"}'
 */
public class Weather  implements Serializable {

    static final long serialVersionUID = 126789345698L;


    private String centername ;
    private String cityX ;
    private String cityY ;
    private String cityname ;
    private String fontColor ;
    private String humidity ;
    private String pyName ;
    private String state1 ;
    private String state2 ;
    private String stateDetailed ;
    private String tem1 ;
    private String tem2 ;
    private String temNow ;
    private String time ;
    private String url ;
    private String windDir ;
    private String windPower ;
    private String windState ;

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM");

    private String rtm;

    public Weather() {
        rtm =sdf.format(System.currentTimeMillis());
    }

    public String getTem2() {
        return tem2;
    }

    public void setTem2(String tem2) {
        this.tem2 = tem2;
    }

    public String getTem1() {
        return tem1;
    }

    public void setTem1(String tem1) {
        this.tem1 = tem1;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getStateDetailed() {
        return stateDetailed;
    }

    public void setStateDetailed(String stateDetailed) {
        this.stateDetailed = stateDetailed;
    }

    public String getPyName() {
        return pyName;
    }

    public void setPyName(String pyName) {
        this.pyName = pyName;
    }

    public String getState2() {
        return state2;
    }

    public void setState2(String state2) {
        this.state2 = state2;
    }

    public String getWindState() {
        return windState;
    }

    public void setWindState(String windState) {
        this.windState = windState;
    }

    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public String getCentername() {
        return centername;
    }

    public void setCentername(String centername) {
        this.centername = centername;
    }

    public String getCityX() {
        return cityX;
    }

    public void setCityX(String cityX) {
        this.cityX = cityX;
    }

    public String getCityY() {
        return cityY;
    }

    public void setCityY(String cityY) {
        this.cityY = cityY;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemNow() {
        return temNow;
    }

    public void setTemNow(String temNow) {
        this.temNow = temNow;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Objects.equal(url, weather.url) &&
                Objects.equal(rtm, weather.rtm);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(url, rtm);
    }

    @Override
    public String toString() {
        try {
            return JacksonUtil.write2JsonStr(this);
        } catch (JsonProcessingException e) {
        }
        return null;
    }
}
