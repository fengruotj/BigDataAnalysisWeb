package com.basic.bigdata.util;

import org.junit.Test;

/**
 * Created by 79875 on 2017/3/24.
 */
public class LinuxShellUitlTest {

    @Test
    public void testExec() throws Exception {
        String exec = LinuxShellUitl.exec("120.77.83.207", "master", "123456", 22, "ls");
        System.out.println(exec);
    }
}
