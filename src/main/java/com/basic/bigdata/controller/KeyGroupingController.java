package com.basic.bigdata.controller;

import com.basic.bigdata.dto.EChartsKeyGroupingLine;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * locate com.basic.bigdata.controller
 * Created by 79875 on 2017/5/8.
 */
@Controller
public class KeyGroupingController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(KeyGroupingController.class);

    @RequestMapping(value = "/echarts/Pie/keyGroupingLoadBalancing"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String echartsPieKeyGroupingLoadBalancing(){
        Jedis jedis = jedisPool.getResource();
        Map<String,Object> objectMap=new HashedMap();
        Set<String> boltStatusKey = jedis.hkeys("boltStatus");
        objectMap.put("legend",boltStatusKey);
        List list=new ArrayList();
        for(String id:boltStatusKey){
            Map map=new HashMap();
            String status = jedis.hget("boltStatus", id);
            map.put("name",id);
            map.put("value",status);
            list.add(map);
        }
        objectMap.put("piedata",list);
        return gson.toJson(objectMap);
    }

    @RequestMapping(value = "/echarts/Line/keyGroupingLoadBalancing"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String echartsLineKeyGroupingLoadBalancing(){
        EChartsKeyGroupingLine eChartsKeyGroupingLine=new EChartsKeyGroupingLine();

        Jedis jedis = jedisPool.getResource();
        Set<String> boltStatusKey = jedis.hkeys("boltStatus");
        eChartsKeyGroupingLine.setTimewordinfo(Arrays.asList(boltStatusKey.toArray()));
        List list=new ArrayList();
        for(String id:boltStatusKey){
            String status = jedis.hget("boltStatus", id);
            list.add(Long.valueOf(status));
        }
        eChartsKeyGroupingLine.setCount(list);
        return gson.toJson(eChartsKeyGroupingLine);
    }

    @RequestMapping(value = "/keygrouping/cleanRedisCacheLoadBalancing"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String cleanRedisCacheLoadBalancing(){
        Jedis jedis = jedisPool.getResource();
        Set<String> boltStatus = jedis.hkeys("boltStatus");
        for(String id:boltStatus){
            jedis.hdel("boltStatus",id);
        }
        logger.info("delete redis cache success");
        return gson.toJson(true);
    }
}
