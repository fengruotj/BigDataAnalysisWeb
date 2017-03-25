package com.basic.bigdata.linux;

import com.jcraft.jsch.JSchException;

/**
 * Created by 79875 on 2017/3/25.
 * 获得集群信息
 */
public abstract class ResourceUsage {
    /**
     * 获得机器资源信息
     * @return
     */
    abstract public float get(String host,String name,String psw, int port) throws JSchException;
}
