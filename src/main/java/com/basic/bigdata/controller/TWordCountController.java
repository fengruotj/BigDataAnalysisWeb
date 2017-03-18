package com.basic.bigdata.controller;

import com.basic.bigdata.dto.EChartsWordCount;
import com.basic.bigdata.model.TWordcount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by 79875 on 2017/3/9.
 */
@Controller
public class TWordCountController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(TWordCountController.class);

    /**
     * 将wordCount统计结果输出到Echarts界面上
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/echarts/wordCountTimeLine"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String wordCountTimeLineEchartsUI() throws IOException {
        //return gsonUtil.readJsonStream(new FileInputStream(StormSpoutGsonFilePath+fileName));
        List<String> timeWordInfo = tWordcountService.getTimeLineWordInfoList();
        List<Long> CountList=tWordcountService.getTimeLineCountList();
        EChartsWordCount wordCount=new EChartsWordCount(timeWordInfo,CountList);
        return gson.toJson(wordCount);
    }

    /**
     * 将输出结果以json的格式保存下来
     * @param fileName 保存的文件名字
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/wordcount/saveresultwordcountTimeLine"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String saveResultTimeLineEchartsUI(@RequestParam String fileName) throws IOException {
        //return gsonUtil.readJsonStream(new FileInputStream(StormSpoutGsonFilePath+fileName));
        List<String> timeWordInfo = tWordcountService.getTimeLineWordInfoList();
        List<Long> CountList=tWordcountService.getTimeLineCountList();
        EChartsWordCount wordCount=new EChartsWordCount(timeWordInfo,CountList);
        gsonUtil.writeJsonStream(new FileOutputStream(StormWordCountGsonFile+fileName+".json"),wordCount);
        return gson.toJson(true);
    }

    /**
     * 将wordCount统计结果输出到Echarts界面上
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/echarts/wordCountFinal"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String wordCountFinalEchartsUI() throws IOException {
        //return gsonUtil.readJsonStream(new FileInputStream(StormSpoutGsonFilePath+fileName));
        List<String> timeWordInfo = tWordcountService.getFinalWordList();
        List<Long> CountList=tWordcountService.getFinalCountList();
        EChartsWordCount wordCount=new EChartsWordCount(timeWordInfo,CountList);
        return gson.toJson(wordCount);
    }

    /**
     * 将输出结果以json的格式保存下来
     * @param fileName 保存的文件名字
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/wordcount/saveresultwordcountFinal"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String saveResultFinalEchartsUI(@RequestParam String fileName) throws IOException {
        //return gsonUtil.readJsonStream(new FileInputStream(StormSpoutGsonFilePath+fileName));
        List<String> timeWordInfo = tWordcountService.getFinalWordList();
        List<Long> CountList=tWordcountService.getFinalCountList();
        EChartsWordCount wordCount=new EChartsWordCount(timeWordInfo,CountList);
        gsonUtil.writeJsonStream(new FileOutputStream(StormWordCountGsonFile+fileName+".json"),wordCount);
        return gson.toJson(true);
    }


    @RequestMapping(value = "/echarts/wordcountAnaylsis"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String wordcountAnaylsis(@RequestParam String fileName) throws IOException {
        String s = gsonUtil.readJsonStream(new FileInputStream(StormWordCountGsonFile + fileName));
        return s;
    }

    /**
     * 数据库分页展示WordCountcount表数据
     * @param page 页
     * @param rows 行
     * @param wordcount 单词计数模型
     * @return
     */
    @RequestMapping(value = "/wordcount/queryByPage"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String querySpoutTupleCountByPage(@RequestParam Integer page, TWordcount wordcount,
                                             @RequestParam Integer rows){
        return tWordcountService.queryTuplecountBypage(wordcount.getWord(),page,rows);
    }
}
