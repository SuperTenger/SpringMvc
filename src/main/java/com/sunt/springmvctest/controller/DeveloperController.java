package com.sunt.springmvctest.controller;

import com.sunt.springmvctest.dao.DeveloperDao;
import com.sunt.springmvctest.model.CommonModel;
import com.sunt.springmvctest.model.DeveloperModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/developer")
public class DeveloperController {

    private DeveloperDao developerDao;

    @Autowired
    DeveloperController(DeveloperDao developerDao) {
        this.developerDao = developerDao;
    }

    @RequestMapping(value = "/api/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/api/developers", method = RequestMethod.GET)
    @ResponseBody
    public CommonModel getAllDevelopers() {
        List<DeveloperModel> developerModels = developerDao.getAllDevelopers();
        CommonModel commonModel = new CommonModel();
        if (developerModels.size() > 0) {
            commonModel.setSuccess();
            commonModel.setData(developerModels);
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    @RequestMapping(value = "/api/developer", method = RequestMethod.GET)
    @ResponseBody
    public CommonModel getDeveloper(String id) {
        DeveloperModel developerModel = developerDao.getDeveloper(id);
        CommonModel commonModel = new CommonModel();
        if (developerModel != null) {
            commonModel.setSuccess();
            commonModel.setData(developerModel);
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    @RequestMapping(value = "/api/developer/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonModel addDeveloper(String name, String site) {
        CommonModel commonModel = new CommonModel();
        DeveloperModel developerModel = new DeveloperModel();
        developerModel.setName(name);
        developerModel.setSite(site);
        if (developerDao.addDeveloper(developerModel)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    @RequestMapping(value = "/api/developer/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonModel updateDeveloper(String id, String name) {
        CommonModel commonModel = new CommonModel();
        if (developerDao.updateDeveloper(id, name)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    @RequestMapping(value = "/api/developer/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonModel deleteDeveloper(String id) {
        CommonModel commonModel = new CommonModel();
        if (developerDao.deleteDeveloper(id)) {
            commonModel.setSuccess();
        }else {
            commonModel.setFail();
        }
        return commonModel;
    }

}
