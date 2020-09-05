package com.atguigu.config;

import com.atguigu.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Liulei
 * @create 2020-08-14 23:12
 */
@ComponentScan(value = {"com.atguigu"},useDefaultFilters = false,
includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class)})
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
    /*配置视图解析器；
        相当于：<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            <property name="prefix" value="/WEB-INF/pages/"></property>
            <property name="suffix" value=".jsp"></property>
        </bean>
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //registry.jsp(); //默认所有的页面前缀：/WEB-INF/，后缀：.jsp
        registry.jsp("/WEB-INF/view/",".jsp");//定制前缀，后缀
    }

    /*配置静态资源访问；
        相当于：<mvc:default-servlet-handler/>
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /*配置拦截器：
        相当于：<mvc:interceptors>
                    <mvc:interceptor>
                        <!--路径是相对于当前项目的，“/controller”不可以省略，路径必须是“/**”-->
                        <mvc:mapping path="/**"/>
                        <!--mvc:exclude-mapping必须放在mvc:mapping下面才不会报错，不可以单独使用-->
                        <mvc:exclude-mapping path="/controller/fileupload"/>
                        <bean id="myInterceptor" class="com.atguigu.interceptor.MyInterceptor"></bean>
                    </mvc:interceptor>
               </mvc:interceptors>
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //此处用抽象类，也可以单写一个类
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
                System.out.println("preHandle……");
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
                System.out.println("postHandle……");
            }

            @Override
            public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
                System.out.println("afterCompletion……");
            }
        }).addPathPatterns("/**");//代表拦截所有请求，“/**”一定要这样写
    }

    /*配置转换器：
        相当于：
            <bean id="conversionServiceFactoryBean" class="org.springframework.context.support.ConversionServiceFactoryBean">
                <property name="converters">
                    <set>
                        <ref bean="myConvert"></ref>  //上步注解生成的名字
                    </set>
                </property>
            </bean>
            <mvc:annotation-driven conversion-service="conversionServiceFactoryBean"></mvc:annotation-driven>  //上步的bean的id
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        //此处用抽象类，也可以单独写一个类
        registry.addConverter(new Converter<String, Person>() {
            @Override
            public Person convert(String s) {
                String[] split = s.split("\\-");
                return new Person(split[0],Integer.valueOf(split[1]));
            }
        });
    }

    /*配置view-controller
        相当于：<mvc:view-controller path="springmvc/testJstlView view-name="success"/>
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/viewCtl").setViewName("success");
    }
    /*配置文件上传
       相当于：
        <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">   //“id”一定要有，且必须是“multipartResolver”
             <!--配置基本属性-->
             <!--配置文件编码-->
             <property name="defaultEncoding" value="UTF-8"></property>
             <!--配置最大上传文件，以字节为单位-->
             <property name="maxUploadSize" value="1024000"></property>
        </bean>

        id必须是“multipartResolver”，否则报错
     */
    @Bean("multipartResolver")
    public CommonsMultipartResolver multipartFile(){
        CommonsMultipartResolver common = new CommonsMultipartResolver();
        common.setDefaultEncoding("utf-8");
        common.setMaxUploadSize(1024000);
        return common;
    }

}
