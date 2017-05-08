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
public class DataBaseController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(DataBaseController.class);

    @RequestMapping(value = "/data/deleteStormBenchData"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String deleteStormBenchData() throws IOException {
        //return gsonUtil.readJsonStream(new FileInputStream(StormSpoutGsonFilePath+fileName));
        tWordcountService.deleteAll();
        tTuplecountService.deleteAll();
        tSpouttuplecountService.deleteAll();
        tHdfsbytecountService.deleteAll();
        return gson.toJson(true);
    }
}
