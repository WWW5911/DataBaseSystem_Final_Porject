package com.example.inAir;

import java.util.List;
import javax.sql.DataSource;

public interface inAirDAO {
    public void setDataSource(DataSource ds);

    public void create(Integer R_ID, Integer A_SiteID);

    public List<inAir> getinAirA(Integer A_SiteID);

    public inAir getinAir(Integer R_ID);

    public List<inAir> listinAirs();

    public void delete(Integer R_ID);

    public void update(Integer R_ID, Integer A_SiteID);
}
