package com.basic.bigdata.util;

import org.junit.Test;

/**
 * Created by 79875 on 2017/3/24.
 */
public class LinuxShellUitlTest {

    @Test
    public void testExec() throws Exception {
        String exec = LinuxShellUitl.exec("root2", "root", "bigdata17", 22, "ls","linuxlog/outls.log");
        System.out.println(exec);
    }
}
