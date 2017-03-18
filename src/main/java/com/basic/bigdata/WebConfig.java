package com.basic.bigdata;

import com.basic.bigdata.interceptor.AdminSecurityInterceptor;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by dell-pc on 2016/4/19.
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 注册静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/static/assets/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LocaleInterceptor());
        registry.addInterceptor(new AdminSecurityInterceptor()).addPathPatterns("/manage*/**");
    }

    // 用于处理编码问题
    @Bean
    public FilterRegistrationBean characterEncodingFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        registration.setFilter(characterEncodingFilter);
        registration.addUrlPatterns("/*");
        registration.addInitParameter("encoding","UTF-8");
        registration.setName("encodingFilter");
        return registration;
    }

    ///解决Hibernate OpenSessionInView
    @Bean
    public FilterRegistrationBean OpenSessionInViewFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        OpenSessionInViewFilter openSessionInViewFilter=new OpenSessionInViewFilter();
        registration.setFilter(openSessionInViewFilter);
        registration.addUrlPatterns("/*");
        registration.addInitParameter("singleSession", "true");
        registration.setName("OpenSessionInViewFilter");
        return registration;
    }

}
