package com.basic.bigdata.controller;

import com.jcraft.jsch.JSchException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 79875 on 2017/3/26.
 */
@Controller
public class ResourceUsageController extends BaseController {

    @RequestMapping(value = "/resoureusage/cpuusage"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String cpuUsage() throws IOException, JSchException {
        Map map=new HashMap<>();
        String[] hosts = clusterhosts.split(",");
        map.put("hosts",hosts);
        List<Float> list=new ArrayList();
        for(String host:hosts){
            float v = cpuUsage.get(host, user, password, 22);
            list.add(v*100);
        }
        map.put("value",list);
        return gson.toJson(map);
    }
}
