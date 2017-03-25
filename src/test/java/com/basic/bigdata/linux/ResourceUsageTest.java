package com.basic.bigdata.linux;

import org.junit.Test;

/**
 * Created by 79875 on 2017/3/25.
 */
public class ResourceUsageTest {

    CpuUsage cpuUsage=CpuUsage.getInstance();
    MemUsage memUsage=MemUsage.getInstance();
    IoUsage ioUsage=IoUsage.getInstance();
    NetUsage netUsage=NetUsage.getInstance();

    @Test
    public void testcpu() throws Exception {
        while(true){
            System.out.println(cpuUsage.get("120.77.83.207", "master", "123456", 22));
            Thread.sleep(5000);
        }
    }

    @Test
    public void testmemory() throws Exception {
        while(true){
            System.out.println(memUsage.get("120.77.83.207", "master", "123456", 22));
            Thread.sleep(5000);
        }
    }

    @Test
    public void testdiskio() throws Exception {
        while(true){
            System.out.println(ioUsage.get("120.77.83.207", "master", "123456", 22));
            Thread.sleep(5000);
        }
    }

    @Test
    public void testNetwork() throws Exception {
        while(true){
            System.out.println(netUsage.get("120.77.83.207", "master", "123456", 22));
            Thread.sleep(5000);
        }
    }
}
