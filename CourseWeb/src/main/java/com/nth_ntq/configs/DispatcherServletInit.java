/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nth_ntq.configs;
import com.nth_ntq.filters.JwtFilter;
import jakarta.servlet.Filter;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author pc
 */


public class DispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
            ThymeleafConfig.class,
            HibernateConfigs.class,
            SpringSecurityConfigs.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
            WebAppContextConfigs.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        String location = "/";
        long maxFileSize = 5242880; // 5MB
        long maxRequestSize = 20971520; // 20MB
        int fileSizeThreshold = 0;

        registration.setMultipartConfig(new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold));
    }
    
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] { new JwtFilter() }; // Filter sẽ áp dụng cho mọi request
    }
}