package com.basic.bigdata.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by 79875 on 2017/3/9.
 */
@Controller
public class DataController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(DataController.class);

    @RequestMapping(value = "/data/deleteStormBenchData"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String deleteData() throws IOException {
        //return gsonUtil.readJsonStream(new FileInputStream(StormSpoutGsonFilePath+fileName));
        tWordcountService.deleteAll();
        tTuplecountService.deleteAll();
        tSpouttuplecountService.deleteAll();
        return gson.toJson(true);
    }
}
