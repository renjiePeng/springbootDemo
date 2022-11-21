package com.prj.config;

import com.prj.convert.TimeStrConvert;
import com.prj.convert.UserConvert;
import com.prj.convert.UserDTOConvert;
import com.prj.interceptor.JsonInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className: com.prj.config.WebConfig
 * @description:
 * @author: pengrj
 * @create: 2022-10-26 11:18
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;

    @Autowired
    private JsonInterceptor interceptor;

    @Resource
    private UserDTOConvert userDTOConvert;

    @Resource
    private UserConvert userConvert;

    @Resource
    private TimeStrConvert timeStrConvert;

//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
//        ObjectMapper mapper = jackson2ObjectMapperBuilder.build();
//        DateFormat dateFormat = mapper.getDateFormat();
//        mapper.setDateFormat(new MyDateFormat(dateFormat));
//        return new MappingJackson2HttpMessageConverter(
//                mapper);
//    }

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new StringToDateFormatter());
//    }

    //定义时间格式转换器
//    @Bean
//    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        converter.setObjectMapper(mapper);
//        return converter;
//    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
//        converter.setObjectMapper(mapper);
//        converters.add(jackson2HttpMessageConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器，配置拦截地址
        registry.addInterceptor(interceptor);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(userDTOConvert);
        registry.addConverter(userConvert);
        registry.addConverter(timeStrConvert);
    }
}
