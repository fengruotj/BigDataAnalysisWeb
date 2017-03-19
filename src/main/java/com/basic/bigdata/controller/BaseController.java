package com.basic.bigdata.controller;

import com.basic.bigdata.service.TAdministratorService;
import com.basic.bigdata.service.TSpouttuplecountService;
import com.basic.bigdata.service.TTuplecountService;
import com.basic.bigdata.service.TWordcountService;
import com.basic.bigdata.util.FreeMarkerUtil;
import com.basic.bigdata.util.GsonUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;

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

}
