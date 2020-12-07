package com.example.ServiceTime;

import java.util.List;
import javax.sql.DataSource;

public interface ServiceTimeDAO {
    public void setDataSource(DataSource ds);

    public void create(Integer id, Integer week, Integer openTime, Integer closeTime);

    public ServiceTime getServiceTime(Integer id);

    public List<ServiceTime> listServiceTimes();

    public void delete(Integer id);

    public void update(Integer id, Integer week, Integer openTime, Integer closeTime);
}
