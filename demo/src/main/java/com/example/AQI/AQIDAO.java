package com.example.AQI;

import java.util.List;
import javax.sql.DataSource;

public interface AQIDAO {
    public void setDataSource(DataSource ds);

    public void create(int SiteID, String SiteName, String S_city, float S_latitude, float S_longitude, float AQI, String Status, float PM25);

    public AQI getAQI(Integer SiteID);

    public List<AQI> listAQIs();

    public void delete(Integer SiteID);

    public void update(Integer id, float AQI, String Status, float PM25);
}
