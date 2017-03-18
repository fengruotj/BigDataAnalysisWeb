package com.basic.bigdata.service;

import com.basic.bigdata.Application;
import com.basic.bigdata.model.TSpouttuplecount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 79875 on 2017/3/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port:0")
@TransactionConfiguration(defaultRollback=false)
@Transactional(propagation= Propagation.REQUIRED)
public class TSpouttuplecountServiceImplTest {

    @Resource
    TSpouttuplecountService spouttuplecountService;

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testMerge() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testQueryALL() throws Exception {
        List<TSpouttuplecount> tSpouttuplecounts = spouttuplecountService.queryALL();
        System.out.println(tSpouttuplecounts.size());
    }

    @Test
    public void testGetTimeLineInfo() throws Exception {
        List<Timestamp> timeLineInfo = spouttuplecountService.getTimeLineInfo();
        for(int i=0;i<timeLineInfo.size();i++){
            System.out.println(timeLineInfo.get(i));
        }

    }

    @Test
    public void testGetSpoutTupleCountByTimeinfo() throws Exception {
        List<Timestamp> timeLineInfo = spouttuplecountService.getTimeLineInfo();
            System.out.println(timeLineInfo.get(2));
            List<TSpouttuplecount> spoutTupleCountByTimeinfo = spouttuplecountService.getSpoutTupleCountByTimeinfo(timeLineInfo.get(2));
            for(int j=0;j<spoutTupleCountByTimeinfo.size();j++){
                System.out.println(spoutTupleCountByTimeinfo.get(j).getTuplecount());
            }
    }

    @Test
    public void testGetSumSpoutTupleCountByTimeinfo() throws Exception {
        List<Timestamp> timeLineInfo = spouttuplecountService.getTimeLineInfo();
        for(int j=0;j<timeLineInfo.size();j++){
            Long sumSpoutTupleCountByTimeinfo = spouttuplecountService.getSumSpoutTupleCountByTimeinfo(timeLineInfo.get(j));
            System.out.println(sumSpoutTupleCountByTimeinfo);
        }
    }

    @Test
    public void test(){

    }

    @Test
    public void testGetSumListSpoutTupleCountByTimeInfo() throws Exception {
        List<Long> sumList = spouttuplecountService.getSumListSpoutTupleCountByTimeInfo();
        System.out.println(sumList);
    }
}
