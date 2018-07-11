package com.sunt.springmvctest.dao;

import com.sunt.springmvctest.model.DeveloperModel;

import java.util.List;

public interface DeveloperDao {

    List<DeveloperModel> getAllDevelopers();

    DeveloperModel getDeveloper(String developerId);

    boolean addDeveloper(DeveloperModel developerModel);

    boolean updateDeveloper(String id,String name);

    boolean deleteDeveloper(String id);

}
