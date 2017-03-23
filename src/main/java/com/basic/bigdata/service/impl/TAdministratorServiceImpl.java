package com.basic.bigdata.service.impl;

import com.basic.bigdata.model.Administrator;
import com.basic.bigdata.service.TAdministratorService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell-pc on 2016/4/22.
 */

@Transactional(propagation= Propagation.REQUIRED)
@Service("tAdministratorService")
public class TAdministratorServiceImpl extends BaseServiceImpl implements TAdministratorService {

    @Override
    public void save(Administrator administrator) {
         tAdministratorDAO.save(administrator);
    }

    @Override
    public void update(Administrator administrator) {
        tAdministratorDAO.getSession().update(administrator);
    }

    @Override
    public void merge(Administrator administrator) {
        tAdministratorDAO.getSession().merge(administrator);
    }

    @Override
    public void delete(Long id) {
        tAdministratorDAO.getSession().delete(get(id));
    }

    @Override
    public Administrator get(Long id) {
        return tAdministratorDAO.getById(id);
    }

    @Override
    public List<Administrator> queryALL() {
        return tAdministratorDAO.findList("from Administrator");
    }

    @Override
    public Administrator findadminByusernameAndPass(String username, String password) {
        Map map=new HashMap();
        map.put("username",username);
        map.put("password",password);
        List byProperty = tAdministratorDAO.findByProperty(map);
        if(byProperty.size()==0){
            return null;
        }else
            return (Administrator) byProperty.get(0);
    }

    @Override
    public String queryTuserByPage(String name, int page, int size) {
        List<Administrator> administrators=tAdministratorDAO.queryTUserByPage(name,page, size);
        Long total=tAdministratorDAO.getCount(name);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Map map=new HashMap();
        map.put("total",total);
        map.put("rows",administrators);
        return gson.toJson(map);
    }
}
