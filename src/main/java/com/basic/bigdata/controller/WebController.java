package com.basic.bigdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

/**
 * Created by dell-pc on 2016/4/22.
 */

@Controller
public class WebController extends BaseController{

    @RequestMapping(value = "/manage")
    public String index(){
        return mainPath+"aindex";
    }

    @RequestMapping(value = "/manage/login")
    public String login(){
        return mainPath+"login";
    }

    @RequestMapping(value = "/temp")
    public String temp(){
        return "temp";
    }

    @RequestMapping("/manage_{var1}_{var2}")
    public String sendFunc(@PathVariable("var1") String var1, @PathVariable("var2") String var2){
        return mainPath+var1+"/"+var2;
    }

    @RequestMapping("/storm/analysis")
    public ModelAndView stormAnaylsis(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("manage/stormbench/stormanalysischarts");
        File boltGsonFile=new File(StormBoltGsonFilePath);
        File spoutGsonFile=new File(StormSpoutGsonFilePath);
        File wordcountGsonFile=new File(StormWordCountGsonFile);
        File hdfsbenchmarkGsonFile=new File(StormHdfsBenchMarkGsonFile);
        modelAndView.addObject("boltGsonFilenames",boltGsonFile.list());
        modelAndView.addObject("spoutGsonFilenames",spoutGsonFile.list());
        modelAndView.addObject("wordcountGsonFilenames",wordcountGsonFile.list());
        modelAndView.addObject("hdfsbenchmarkGsonFilenames",hdfsbenchmarkGsonFile.list());
        return modelAndView;
    }
}
