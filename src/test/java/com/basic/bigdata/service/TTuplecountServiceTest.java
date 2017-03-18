package com.basic.bigdata.service;

import com.basic.bigdata.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 79875 on 2017/3/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port:0")
@TransactionConfiguration(defaultRollback=false)
@Transactional(propagation= Propagation.REQUIRED)
public class TTuplecountServiceTest {

    @Autowired
    TTuplecountService tTuplecountService;

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
        List<Long> longList = tTuplecountService.getSumListTupleCountByTimeInfo();
        System.out.println(longList);
    }
}
