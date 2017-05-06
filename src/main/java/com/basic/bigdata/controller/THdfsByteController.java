package com.basic.bigdata.controller;

import com.basic.bigdata.dto.EChartsHdfsByteCount;
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
 * Created by 79875 on 2017/3/19.
 */
@Controller
public class THdfsByteController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(THdfsByteController.class);

    /**
     * 将tupleCount统计结果输出到Echarts界面上
     * @throws IOException
     */
    @RequestMapping(value = "/echarts/hdfsbytecountTupleCount"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String hdfsbyteCountEchartsUI() throws IOException {
        //return gsonUtil.readJsonStream(new FileInputStream(StormSpoutGsonFilePath+fileName));
        List<Timestamp> timeLineInfo = tHdfsbytecountService.getTimeLineInfo();
        List<Long> tupleCountList=tHdfsbytecountService.getSumListTupleCountByTimeInfo();
        EChartsHdfsByteCount byteCount=new EChartsHdfsByteCount(timeLineInfo,tupleCountList);
        return gson.toJson(byteCount);
    }

    /**
     * 将输出结果以json的格式保存下来
     * @param fileName 保存的文件名字
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/bolt/saveresulthdfsbytecount"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String saveResultEchartsUI(@RequestParam String fileName) throws IOException {
        List<Timestamp> timeLineInfo = tHdfsbytecountService.getTimeLineInfo();
        List<Long> tupleCountList=tHdfsbytecountService.getSumListTupleCountByTimeInfo();
        EChartsHdfsByteCount byteCount=new EChartsHdfsByteCount(timeLineInfo,tupleCountList);
        gsonUtil.writeJsonStream(new FileOutputStream(StormHdfsBenchMarkGsonFile+fileName+".json"),byteCount);
        return gson.toJson(true);
    }

    @RequestMapping(value = "/echarts/hdfsByteAnaylsis"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String boltTupleAnaylsis(@RequestParam String fileName) throws IOException {
        String s = gsonUtil.readJsonStream(new FileInputStream(StormHdfsBenchMarkGsonFile + fileName));
        return s;
    }

    /**
     * 数据库分页展示tuplecount表数据
     * @param page 页
     * @param rows 行
     * @return
     */
    @RequestMapping(value = "/hdfsbyte/queryByPage"
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String queryHdfsByteCountByPage(@RequestParam Integer page,
                                        @RequestParam Integer rows){
        return tHdfsbytecountService.queryTuplecountBypage(page,rows);
    }
}
