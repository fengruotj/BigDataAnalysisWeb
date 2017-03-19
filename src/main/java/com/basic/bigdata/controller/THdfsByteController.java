package com.basic.bigdata.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by 79875 on 2017/3/19.
 */
@Controller
public class THdfsByteController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(THdfsByteController.class);

    @RequestMapping(value = "/echarts/hdfsByteAnaylsis"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String boltTupleAnaylsis(@RequestParam String fileName) throws IOException {
        String s = gsonUtil.readJsonStream(new FileInputStream(StormHdfsBenchMarkGsonFile + fileName));
        return s;
    }
}
