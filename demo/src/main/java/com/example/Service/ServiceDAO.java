package com.example.Service;

import java.util.List;
import javax.sql.DataSource;

public interface ServiceDAO {
    public void setDataSource(DataSource ds);

    public void create(Integer S_ID, Integer R_ID);

    public List<Service> getServiceR(Integer R_ID);

    public Service getService(Integer S_ID);

    public List<Service> listServices();

    public void delete(Integer S_ID);

    public void update(Integer S_ID, Integer R_ID);
}
