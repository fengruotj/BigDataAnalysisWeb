package com.basic.bigdata.service;

import com.basic.bigdata.model.Administrator;

/**
 * Created by dell-pc on 2016/4/22.
 */
public interface TAdministratorService extends BaseService<Administrator>{
    public Administrator findadminByusernameAndPass(String username, String password);

    public String queryTuserByPage(String name, int page, int size);
}
