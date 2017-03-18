package com.basic.bigdata.util;

import org.junit.Test;

import java.io.FileInputStream;

/**
 * Created by 79875 on 2017/3/8.
 */
public class GsonUtilTest {

    @Test
    public void testWriteJsonStream() throws Exception {

    }

    @Test
    public void testReadJsonStream() throws Exception {
        GsonUtil gsonUtil=new GsonUtil();
        String s = gsonUtil.readJsonStream(new FileInputStream("D:\\storm\\SpoutGson\\stormTest1.json"));
        System.out.printf(s);
    }
}
