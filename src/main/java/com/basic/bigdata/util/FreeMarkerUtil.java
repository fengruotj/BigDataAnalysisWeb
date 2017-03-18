package com.basic.bigdata.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;

/**
 * Created by dell-pc on 2016/4/20.
 */
@Component("freeMarkerUtil")
public class FreeMarkerUtil {
    private static Configuration configuration = null;

    /*
    * spring注入的时候回调用它的默认构造方法
    *
     */
    private FreeMarkerUtil(){
        if (null == configuration) {
            configuration = new Configuration(Configuration.VERSION_2_3_22);  // 这里Configurantion对象不能有两个，否则多线程访问会报错
            configuration.setDefaultEncoding("utf-8");
            configuration.setClassicCompatible(true);
        }
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public void process(Map<?, ?> root, String template, Writer out){

        if (null == root ) {
            throw new RuntimeException("数据不能为空");
        }

        if (null == template) {
            throw new RuntimeException("模板文件不能为空");
        }
        String templatePath = template.substring(0, template.lastIndexOf("/"));
        String templateName = template.substring(template.lastIndexOf("/") + 1, template.length());

        configuration.setClassForTemplateLoading(FreeMarkerUtil.class, templatePath);
        Template t = null;

        try {
            t = configuration.getTemplate(templateName);
            t.process(root, out);  // 这里out是一个输出地址，可以输出到任何位置，如控制台，网页等
            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取指定目录下的模板文件
     * @param name       模板文件的名称
     * @param pathPrefix 模板文件的目录
     */
    public Template getTemplate(String name, String pathPrefix) throws IOException{
        configuration.setClassForTemplateLoading(this.getClass(), pathPrefix); //设置模板文件的目录
        configuration.setDefaultEncoding("UTF-8");       //Set the default charset of the template files
        Template temp = configuration.getTemplate(name); //在模板文件目录中寻找名为"name"的模板文件
        return temp; //此时FreeMarker就会到类路径下的"pathPrefix"文件夹中寻找名为"name"的模板文件
    }

    /**
     * 根据模板文件输出内容到控制台
     * @param name       模板文件的名称
     * @param pathPrefix 模板文件的目录
     * @param rootMap    模板的数据模型
     */
    public void print(String name, String pathPrefix, Map<String,Object> rootMap) throws TemplateException, IOException{
        this.getTemplate(name, pathPrefix).process(rootMap, new PrintWriter(System.out));
    }

    /**
     * 根据模板文件输出内容到指定的文件中
     * @param name       模板文件的名称
     * @param pathPrefix 模板文件的目录
     * @param rootMap    模板的数据模型
     * @param file       内容的输出文件
     */
    public void printFile(String name, String pathPrefix, Map<String,Object> rootMap, File file) throws TemplateException, IOException {
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        this.getTemplate(name, pathPrefix).process(rootMap, out); //将模板文件内容以UTF-8编码输出到相应的流中
        if(null != out){
            out.close();
        }
    }
}
