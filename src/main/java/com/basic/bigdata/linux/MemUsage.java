package com.basic.bigdata.linux;

/**
 * Created by 79875 on 2017/3/25.
 */

import com.basic.bigdata.util.LinuxShellUitl;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 采集内存使用率
 */
public class MemUsage extends ResourceUsage{

    private static Logger log = LoggerFactory.getLogger(MemUsage.class);

    private static MemUsage INSTANCE = new MemUsage();

    private MemUsage(){

    }

    public static MemUsage getInstance(){
        return INSTANCE;
    }

    /**
     * Purpose:采集内存使用率
     * @return float,内存使用率,小于1
     */
    @Override
    public float get(String host,String name,String psw, int port) throws JSchException {
        log.info("开始收集memory使用率");
        float memUsage = 0.0f;
        Session session= LinuxShellUitl.getSession(host, name, psw, port);
        ChannelExec openChannel=null;
        try {
            String command = "cat /proc/meminfo";
            openChannel= (ChannelExec) session.openChannel("exec");
            openChannel.setCommand(command);
            int exitStatus = openChannel.getExitStatus();
            openChannel.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(openChannel.getInputStream()));
            String line = null;
            int count = 0;
            long totalMem = 0, freeMem = 0;
            while((line=in.readLine()) != null){
                log.info(line);
                String[] memInfo = line.split("\\s+");
                if(memInfo[0].startsWith("MemTotal")){
                    totalMem = Long.parseLong(memInfo[1]);
                }
                if(memInfo[0].startsWith("MemFree")){
                    freeMem = Long.parseLong(memInfo[1]);
                }
                memUsage = 1- (float)freeMem/(float)totalMem;
                if(++count == 2){
                    break;
                }
            }
            log.info("本节点内存使用率为: " + memUsage);
            in.close();
            openChannel.disconnect();
            session.disconnect();
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error("MemUsage发生InstantiationException. " + e.getMessage());
            log.error(sw.toString());
        }
        return memUsage;
    }

}
