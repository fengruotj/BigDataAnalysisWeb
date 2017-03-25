package com.basic.bigdata.linux;

import com.basic.bigdata.util.LinuxShellUitl;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 采集网络带宽使用率
 */
public class NetUsage extends ResourceUsage {

    private static Logger log = LoggerFactory.getLogger(NetUsage.class);
    private static NetUsage INSTANCE = new NetUsage();
    private final static float TotalBandwidth = 1000;   //网口带宽,Mbps

    private NetUsage(){

    }

    public static NetUsage getInstance(){
        return INSTANCE;
    }

    /**
     * @Purpose:采集网络带宽使用率
     * @return float,网络带宽使用率,小于1
     */
    @Override
    public float get(String host,String name,String psw, int port) throws JSchException {
        log.info("开始收集网络带宽使用率");
        float netUsage = 0.0f;
        Session session= LinuxShellUitl.getSession(host, name, psw, port);
        ChannelExec openChannel=null;
        ChannelExec openChannel2=null;
        try {
            String command = "cat /proc/net/dev";
            //第一次采集流量数据
            long startTime = System.currentTimeMillis();
            openChannel= (ChannelExec) session.openChannel("exec");
            openChannel.setCommand(command);
            int exitStatus = openChannel.getExitStatus();
            openChannel.connect();
            BufferedReader in1 = new BufferedReader(new InputStreamReader(openChannel.getInputStream()));
            String line = null;
            long inSize1 = 0, outSize1 = 0;
            while((line=in1.readLine()) != null){
                line = line.trim();
                if(line.startsWith("eth0")){
                    log.info(line);
                    String[] temp = line.split("\\s+");
                    inSize1 = Long.parseLong(temp[1].substring(5)); //Receive bytes,单位为Byte
                    outSize1 = Long.parseLong(temp[9]);             //Transmit bytes,单位为Byte
                    break;
                }
            }
            in1.close();
            openChannel.disconnect();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                log.error("NetUsage休眠时发生InterruptedException. " + e.getMessage());
                log.error(sw.toString());
            }
            //第二次采集流量数据
            long endTime = System.currentTimeMillis();
            openChannel2 = (ChannelExec) session.openChannel("exec");
            openChannel2.setCommand(command);
            int exitStatus2 = openChannel2.getExitStatus();
            openChannel2.connect();
            BufferedReader in2 = new BufferedReader(new InputStreamReader(openChannel2.getInputStream()));
            long inSize2 = 0 ,outSize2 = 0;
            while((line=in2.readLine()) != null){
                line = line.trim();
                if(line.startsWith("eth0")){
                    log.info(line);
                    String[] temp = line.split("\\s+");
                    inSize2 = Long.parseLong(temp[1].substring(5));
                    outSize2 = Long.parseLong(temp[9]);
                    break;
                }
            }
            if(inSize1 != 0 && outSize1 !=0 && inSize2 != 0 && outSize2 !=0){
                float interval = (float)(endTime - startTime)/1000;
                //网口传输速度,单位为bps
                float curRate = (float)(inSize2 - inSize1 + outSize2 - outSize1)*8/(1000000*interval);
                netUsage = curRate/TotalBandwidth;
                log.info("本节点网口速度为: " + curRate + "Mbps");
                log.info("本节点网络带宽使用率为: " + netUsage);
            }
            in2.close();
            openChannel2.disconnect();
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error("NetUsage发生InstantiationException. " + e.getMessage());
            log.error(sw.toString());
        }
        session.disconnect();
        return netUsage;
    }

}
