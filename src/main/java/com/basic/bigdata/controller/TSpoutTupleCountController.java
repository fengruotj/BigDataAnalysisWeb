package com.basic.bigdata.controller;

import com.basic.bigdata.dto.EChartsSpoutTupleCount;
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
 * Created by 79875 on 2017/3/8.
 */
@Controller
public class TSpoutTupleCountController extends BaseController{

    /**
     * 将spoutCount统计结果输出到Echarts界面上
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/echarts/spoutCountTupleCount"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String spoutEchartsUI() throws IOException {
        //return gsonUtil.readJsonStream(new FileInputStream(StormSpoutGsonFilePath+fileName));
        List<Timestamp> timeLineInfo = tSpouttuplecountService.getTimeLineInfo();
        List<Long> tupleCountList=tSpouttuplecountService.getSumListSpoutTupleCountByTimeInfo();
        EChartsSpoutTupleCount spoutTupleCount=new EChartsSpoutTupleCount(timeLineInfo,tupleCountList);
        return gson.toJson(spoutTupleCount);
    }

    /**
     * 将输出结果以json的格式保存下来
     * @param fileName 保存的文件名字
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/spout/saveresultspoutcount"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String saveResultEchartsUI(@RequestParam String fileName) throws IOException {
        List<Timestamp> timeLineInfo = tSpouttuplecountService.getTimeLineInfo();
        List<Long> tupleCountList=tSpouttuplecountService.getSumListSpoutTupleCountByTimeInfo();
        EChartsSpoutTupleCount spoutTupleCount=new EChartsSpoutTupleCount(timeLineInfo,tupleCountList);
        gsonUtil.writeJsonStream(new FileOutputStream(StormSpoutGsonFilePath+fileName+".json"),spoutTupleCount);
        return gson.toJson(true);
    }

    @RequestMapping(value = "/echarts/spoutTupleAnaylsis"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String spoutTupleAnaylsis(@RequestParam String fileName) throws IOException {
        String s = gsonUtil.readJsonStream(new FileInputStream(StormSpoutGsonFilePath + fileName));
        return s;
    }

    /**
     * 数据库分页展示Spouttuplecount表数据
     * @param page 页
     * @param rows 行
     * @return
     */
    @RequestMapping(value = "/spouttuplecount/queryByPage"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String querySpoutTupleCountByPage(@RequestParam Integer page,
                                        @RequestParam Integer rows){
        return tSpouttuplecountService.querySpoutTuplecountBypage(page,rows);
    }
}
