package com.qcby.lxt.vueueditor.config;

import com.qcby.lxt.vueueditor.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @className: WebMvcConfig
 * @description:
 * @author: lxt
 * @create: 2021-07-22 23:54
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    /**
     * 文件上传路径前缀
     */
    @Value("${file.path.prefix}")
    public String filePrefix;
    /**
     * 本地磁盘目录
     */
    @Value("${file.path.upload}")
    public String uploadLocalPath;

    /**
     * @Title: addResourceHandlers
     * @Description:  映射本地磁盘为静态目录
     * @param: registry
     * @throws:
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        FileUtil.INSTANCE.setFilePrefix(filePrefix);
        FileUtil.INSTANCE.setUploadLocalPath(uploadLocalPath);
        registry.addResourceHandler(filePrefix +"/**").addResourceLocations("file:"+uploadLocalPath);
    }

    /**
     * 跨域配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}

