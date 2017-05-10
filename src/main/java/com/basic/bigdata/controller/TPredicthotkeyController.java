package com.basic.bigdata.controller;

import com.basic.bigdata.dto.EchartsSyopsisMapMemory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * locate com.basic.bigdata.controller
 * Created by 79875 on 2017/5/9.
 */
@Controller
public class TPredicthotkeyController extends BaseController {

    /**
     * 统计PredictHotKey中SyopsisHashMap 占用内存大小
     *
     * @return
     */
    @RequestMapping(value = "/echarts/SyopsisMapMemory"
            , produces = "application/json;charset=UTF-8")
    @ResponseBody()
    public String SyopsisMapMemoryEchartsUI() {
        List<Long> idList = tPredicthotkeyService.getIdList();
        List<Integer> tupleCountList = tPredicthotkeyService.getKeySizeList();
        for (int i = 0; i < tupleCountList.size(); i++) {
            Integer aLong = tupleCountList.get(i);
            tupleCountList.set(i, (aLong * 24 + 48));
        }
        EchartsSyopsisMapMemory syopsisMapMemory = new EchartsSyopsisMapMemory(idList, tupleCountList);
        return gson.toJson(syopsisMapMemory);
    }
}
