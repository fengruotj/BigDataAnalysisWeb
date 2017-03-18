package com.basic.bigdata.controller;

import com.basic.bigdata.dto.EChartsTupleCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 79875 on 2017/3/9.
 */
@Controller
public class TTupleCountController  extends BaseController{
    private Logger logger = LoggerFactory.getLogger(TTupleCountController.class);

    /**
     * 将tupleCount统计结果输出到Echarts界面上
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/echarts/tupleCountTupleCount"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String tupleCountEchartsUI() throws IOException {
        //return gsonUtil.readJsonStream(new FileInputStream(StormSpoutGsonFilePath+fileName));
        List<Timestamp> timeLineInfo = tTuplecountService.getTimeLineInfo();
        List<Long> tupleCountList=tTuplecountService.getSumListTupleCountByTimeInfo();
        EChartsTupleCount tupleCount=new EChartsTupleCount(timeLineInfo,tupleCountList);
        return gson.toJson(tupleCount);
    }

    /**
     * 将输出结果以json的格式保存下来
     * @param fileName 保存的文件名字
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bolt/saveresulttuplecount"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String saveResultEchartsUI(@RequestParam String fileName) throws IOException {
        List<Timestamp> timeLineInfo = tTuplecountService.getTimeLineInfo();
        List<Long> tupleCountList=tTuplecountService.getSumListTupleCountByTimeInfo();
        EChartsTupleCount tupleCount=new EChartsTupleCount(timeLineInfo,tupleCountList);
        gsonUtil.writeJsonStream(new FileOutputStream(StormBoltGsonFilePath+fileName+".json"),tupleCount);
        return gson.toJson(true);
    }

    @RequestMapping(value = "/echarts/boltTupleAnaylsis"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String boltTupleAnaylsis(@RequestParam String fileName) throws IOException {
        String s = gsonUtil.readJsonStream(new FileInputStream(StormBoltGsonFilePath + fileName));
        return s;
    }

    /**
     * 数据库分页展示tuplecount表数据
     * @param page 页
     * @param rows 行
     * @return
     */
    @RequestMapping(value = "/tuplecount/queryByPage"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String queryTupleCountByPage(@RequestParam Integer page,
                                        @RequestParam Integer rows){
        return tTuplecountService.queryTuplecountBypage(page,rows);
    }

}
