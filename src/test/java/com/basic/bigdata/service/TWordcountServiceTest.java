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
public class TWordcountServiceTest {

    @Autowired
    TWordcountService wordcountService;

    @Test
    public void testGetTimeWordInfList() throws Exception {
        List<String> infoList = wordcountService.getTimeLineWordInfoList();
        System.out.println(infoList);
    }

    @Test
    public void testGetCountList() throws Exception {
        List<Long> countList = wordcountService.getTimeLineCountList();
        System.out.println(countList);
    }
}
