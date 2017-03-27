package com.basic.bigdata.linux;

import com.basic.bigdata.util.LinuxShellUitl;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by 79875 on 2017/3/25.
 * 采集CPU使用率
 */
public class CpuUsage extends ResourceUsage {

    private static Logger log = Logger.getLogger(CpuUsage.class);
    private static CpuUsage INSTANCE = new CpuUsage();

    private CpuUsage(){

    }

    public static CpuUsage getInstance(){
        return INSTANCE;
    }

    /**
     * Purpose:采集CPU使用率
     * @return float,CPU使用率,小于1
     */
    @Override
    public float get(String host,String name,String psw, int port) throws JSchException {
        log.info("开始收集cpu使用率");
        float cpuUsage = 0;
        ChannelExec openChannel1=null;
        ChannelExec openChannel2=null;
        Session session= LinuxShellUitl.getSession(host, name, psw, port);
        try {
            String command = "cat /proc/stat";
            openChannel1= (ChannelExec) session.openChannel("exec");
            openChannel1.setCommand(command);
            int exitStatus = openChannel1.getExitStatus();
            openChannel1.connect();
            //第一次采集CPU时间
            long startTime = System.currentTimeMillis();
            BufferedReader in1 = new BufferedReader(new InputStreamReader(openChannel1.getInputStream()));
            String line = null;
            long idleCpuTime1 = 0, totalCpuTime1 = 0;	//分别为系统启动后空闲的CPU时间和总的CPU时间
            while((line=in1.readLine()) != null){
                if(line.startsWith("cpu")){
                    line = line.trim();
                    log.info(line);
                    String[] temp = line.split("\\s+");
                    idleCpuTime1 = Long.parseLong(temp[4]);
                    for(String s : temp){
                        if(!s.equals("cpu")){
                            totalCpuTime1 += Long.parseLong(s);
                        }
                    }
                    log.info("IdleCpuTime: " + idleCpuTime1 + ", " + "TotalCpuTime" + totalCpuTime1);
                    break;
                }
            }
            in1.close();
            openChannel1.disconnect();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                log.error("CpuUsage休眠时发生InterruptedException. " + e.getMessage());
                log.error(sw.toString());
            }
            //第二次采集CPU时间
            long endTime = System.currentTimeMillis();
            session= LinuxShellUitl.getSession(host, name, psw, port);
            openChannel2= (ChannelExec) session.openChannel("exec");
            openChannel2.setCommand(command);
            int exitStatus2 = openChannel2.getExitStatus();
            openChannel2.connect();
            BufferedReader in2 = new BufferedReader(new InputStreamReader(openChannel2.getInputStream()));
            long idleCpuTime2 = 0, totalCpuTime2 = 0;	//分别为系统启动后空闲的CPU时间和总的CPU时间
            while((line=in2.readLine()) != null){
                if(line.startsWith("cpu")){
                    line = line.trim();
                    log.info(line);
                    String[] temp = line.split("\\s+");
                    idleCpuTime2 = Long.parseLong(temp[4]);
                    for(String s : temp){
                        if(!s.equals("cpu")){
                            totalCpuTime2 += Long.parseLong(s);
                        }
                    }
                    log.info("IdleCpuTime: " + idleCpuTime2 + ", " + "TotalCpuTime" + totalCpuTime2);
                    break;
                }
            }
            if(idleCpuTime1 != 0 && totalCpuTime1 !=0 && idleCpuTime2 != 0 && totalCpuTime2 !=0){
                cpuUsage = 1 - (float)(idleCpuTime2 - idleCpuTime1)/(float)(totalCpuTime2 - totalCpuTime1);
                log.info("本节点CPU使用率为: " + cpuUsage);
            }
            in2.close();
            openChannel2.disconnect();
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error("CpuUsage发生InstantiationException. " + e.getMessage());
            log.error(sw.toString());
        }
        session.disconnect();
        return cpuUsage;
    }

}
