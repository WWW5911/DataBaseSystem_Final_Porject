package com.example.schoolHighter;

import java.util.List;
import javax.sql.DataSource;

public interface SchoolHighterDAO {
    public void setDataSource(DataSource ds);

    public void create(String name, Integer grade);

    public SchoolHighter getSchoolHighter(Integer id);

    public List<SchoolHighter> listSchoolHighters();

    public void delete(Integer id);

    public void update(Integer id, Integer grade);
}
