package com.sunt.springmvctest.dao;

import com.sunt.springmvctest.model.DeveloperModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("developerDAOImpl")
public class DeveloperDaoImpl implements DeveloperDao {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    private List<DeveloperModel> query(String sql){
        final List<DeveloperModel> developerModels = new ArrayList<>();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String site = resultSet.getString(3);
                DeveloperModel developerModel = new DeveloperModel();
                developerModel.setId(id);
                developerModel.setName(name);
                developerModel.setSite(site);
                developerModels.add(developerModel);
            }
        });
        return developerModels;
    }

    @Override
    public List<DeveloperModel> getAllDevelopers() {
        String sql = "select * from developer";
        return query(sql);
    }

    @Override
    public DeveloperModel getDeveloper(String developerId) {
        String sql = "select * from developer where id =" + developerId;
        List<DeveloperModel> developerModels = query(sql);
        if(developerModels.size()>0){
            return developerModels.get(0);
        }else {
            return null;
        }
    }

    @Override
    public boolean addDeveloper(DeveloperModel developerModel) {
        String sql = "insert into developer(name,site) values(" +
                "'"+developerModel.getName()+"',"
                +"'"+developerModel.getSite()+"'"+ ");";
        try {
            jdbcTemplate.execute(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateDeveloper(String id, String name) {
        String sql = "update developer set name='" + name + "' where id=" + id;
        try{
            jdbcTemplate.execute(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteDeveloper(String id) {
        String sql = "delete from developer where id = " + id;
        try {
            jdbcTemplate.execute(sql);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
