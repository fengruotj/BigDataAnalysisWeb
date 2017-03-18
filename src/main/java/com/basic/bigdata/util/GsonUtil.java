package com.basic.bigdata.util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by 79875 on 2017/3/8.
 */
@Component("gsonUtil")
public class GsonUtil {

    Logger logger= LoggerFactory.getLogger(GsonUtil.class);

    private Gson gson=new Gson();

    public void writeJsonStream(OutputStream out, Object messages) throws IOException {
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("  ");
        gson.toJson(messages,Object.class,writer);
        writer.close();
    }

    public String readJsonStream(InputStream in) throws IOException {
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(in));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
