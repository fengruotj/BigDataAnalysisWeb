package com.basic.bigdata.linux;

import com.basic.bigdata.util.LinuxShellUitl;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by 79875 on 2017/3/25.
 * 采集磁盘IO使用率
 */
public class IoUsage extends ResourceUsage{

    private static Logger log = LoggerFactory.getLogger(IoUsage.class);
    private static IoUsage INSTANCE = new IoUsage();

    private IoUsage(){

    }

    public static IoUsage getInstance(){
        return INSTANCE;
    }

    /**
     * @Purpose:采集磁盘IO使用率
     * @return float,磁盘IO使用率,小于1
     */
    @Override
    public float get(String host,String name,String psw, int port) throws JSchException {
        log.info("开始收集磁盘IO使用率");
        float ioUsage = 0.0f;
        Session session= LinuxShellUitl.getSession(host, name, psw, port);
        ChannelExec openChannel=null;
        try {
            String command = "iostat -d -x";
            openChannel= (ChannelExec) session.openChannel("exec");
            openChannel.setCommand(command);
            int exitStatus = openChannel.getExitStatus();
            openChannel.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(openChannel.getInputStream()));
            String line = null;
            int count =  0;
            while((line=in.readLine()) != null){
                if(++count >= 4){
//                  log.info(line);
                    String[] temp = line.split("\\s+");
                    if(temp.length > 1){
                        float util =  Float.parseFloat(temp[temp.length-1]);
                        ioUsage = (ioUsage>util)?ioUsage:util;
                    }
                }
            }
            if(ioUsage > 0){
                log.info("本节点磁盘IO使用率为: " + ioUsage/100);
                ioUsage /= 100;
            }
            in.close();
            openChannel.disconnect();
            session.disconnect();
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error("IoUsage发生InstantiationException. " + e.getMessage());
            log.error(sw.toString());
        }
        return ioUsage;
    }

}
