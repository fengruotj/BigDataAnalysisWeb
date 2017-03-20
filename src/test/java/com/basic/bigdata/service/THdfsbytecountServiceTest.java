package com.basic.bigdata.service;

import com.basic.bigdata.Application;
import com.basic.bigdata.model.THdfsbytecount;
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

/**
 * Created by 79875 on 2017/3/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port:0")
@TransactionConfiguration(defaultRollback=false)
@Transactional(propagation= Propagation.REQUIRED)
public class THdfsbytecountServiceTest {

    @Resource
    THdfsbytecountService hdfsbytecountService;

    @Test
    public void save() throws Exception {
        hdfsbytecountService.save(new THdfsbytecount(new Timestamp(System.currentTimeMillis()),255L));
    }

    @Test
    public void testGetTimeLineInfo() throws Exception {

    }

    @Test
    public void testGetTupleCountByTimeinfo() throws Exception {

    }

    @Test
    public void testGetSumTupleCountByTimeinfo() throws Exception {

    }

    @Test
    public void testGetSumListTupleCountByTimeInfo() throws Exception {

    }

    @Test
    public void testDeleteAll() throws Exception {

    }

    @Test
    public void testQueryTuplecountBypage() throws Exception {

    }
}
