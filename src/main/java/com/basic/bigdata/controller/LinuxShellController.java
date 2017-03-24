package com.basic.bigdata.controller;

import com.basic.bigdata.util.LinuxShellUitl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by 79875 on 2017/3/24.
 */
@Controller
public class LinuxShellController extends BaseController{

    private static Logger logger= LoggerFactory.getLogger(LinuxShellController.class);

    @RequestMapping(value = "/linux/submitSentencesStormJar"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String submitsentencesStormJar(@RequestParam Integer workernum,
                                          @RequestParam Integer spoutnum,@RequestParam Integer wordcountboltnum) throws IOException {
        logger.info("workernum->" +workernum+" spoutnum->"+spoutnum + " wordcountboltnum->"+wordcountboltnum);

        return "";
    }

    @RequestMapping(value = "/linux/submitSocketStormJar"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String submitSocketStormJar(@RequestParam Integer workernum,
                                          @RequestParam Integer spoutnum,@RequestParam Integer wordcountboltnum) throws IOException {
        logger.info("workernum->" +workernum+" spoutnum->"+spoutnum + " wordcountboltnum->"+wordcountboltnum);

        return "";
    }

    private String execLinuxShell(String commnd,String outFile) throws IOException {
       return LinuxShellUitl.exec(hostname, user, password, 22, commnd,outFile);
    }
}
