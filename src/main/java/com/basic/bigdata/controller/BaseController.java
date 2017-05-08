package com.basic.bigdata.controller;

import com.basic.bigdata.linux.CpuUsage;
import com.basic.bigdata.linux.IoUsage;
import com.basic.bigdata.linux.MemUsage;
import com.basic.bigdata.linux.NetUsage;
import com.basic.bigdata.service.*;
import com.basic.bigdata.util.FreeMarkerUtil;
import com.basic.bigdata.util.GsonUtil;
import com.basic.bigdata.util.JedisPoolUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dell-pc on 2016/4/21.
 */
public class BaseController {

    //后台项目基础url
    protected String mainPath="manage/";

    @Value("#{prop.StormBoltGsonFile}")
    protected String StormBoltGsonFilePath;

    @Value("#{prop.StormSpoutGsonFile}")
    protected String StormSpoutGsonFilePath;

    @Value("#{prop.StormWordCountGsonFile}")
    protected String StormWordCountGsonFile;

    @Value("#{prop.StromHdfsBenchMarkGsonFile}")
    protected String StormHdfsBenchMarkGsonFile;


    @Value("#{prop.hostname}")
    protected String hostname;
    @Value("#{prop.user}")
    protected String user;
    @Value("#{prop.password}")
    protected String password;
    @Value("#{prop.stormSentenceJar}")
    protected String stormSentenceJar;
    @Value("#{prop.stormSocketJar}")
    protected String stormSocketJar;
    @Value("#{prop.stormKafkaJar}")
    protected String stormKafkaJar;
    @Value("#{prop.stormHdfsJar}")
    protected String stormHdfsJar;
    @Value("#{prop.sparkSocektJar}")
    protected String sparkSocektJar;
    @Value("#{prop.sparkKafkajar}")
    protected String sparkKafkajar;

    @Value("#{prop.clusterhosts}")
    protected String clusterhosts;

    @ModelAttribute("BasePath")
    public String getBasePath(HttpServletRequest httpServletRequest){
        return httpServletRequest.getContextPath();
    }

    protected Gson gson = new Gson();

    protected static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected FreeMarkerUtil freeMarkerUtil;

    @Autowired
    protected GsonUtil gsonUtil;

    @Autowired
    protected TAdministratorService tAdministratorService;

    @Autowired
    protected TSpouttuplecountService tSpouttuplecountService;

    @Autowired
    protected TTuplecountService tTuplecountService;

    @Autowired
    protected TWordcountService tWordcountService;

    @Autowired
    protected THdfsbytecountService tHdfsbytecountService;

    protected CpuUsage cpuUsage=CpuUsage.getInstance();

    protected MemUsage memUsage=MemUsage.getInstance();

    protected IoUsage ioUsage=IoUsage.getInstance();

    protected NetUsage netUsage=NetUsage.getInstance();

    protected JedisPool jedisPool= JedisPoolUtil.getJedisPoolInstance();
}
