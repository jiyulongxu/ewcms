/**
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */
package com.ewcms.plugin.datasource.generate.factory.init;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ewcms.plugin.datasource.generate.factory.init.bean.BeanForInterfaceFactoryable;

/**
 * 数据库服务工厂,在系统启动时自动加载
 * 
 * @author 吴智俊
 */
@Service
public class EwcmsDataSourceFactory implements EwcmsDataSourceFactoryable, InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(EwcmsDataSourceFactory.class);
    
    @Autowired
    private BeanForInterfaceFactoryable factory = null;
    private Map<String,String> beanForInterfaceMappings = null;
    
    public BeanForInterfaceFactoryable getFactory() {
        return factory;
    }

    public void setFactory(BeanForInterfaceFactoryable factory) {
        this.factory = factory;
    }

    public Map<String,String> getBeanForInterfaceMappings() {
        return beanForInterfaceMappings;
    }

    public void setBeanForInterfaceMappings(Map<String,String> beanForInterfaceMappings) {
        this.beanForInterfaceMappings = beanForInterfaceMappings;
    }

    private static Map<String, String> serviceDefinitionMap;

    static {
        serviceDefinitionMap = new HashMap<String, String>();

        serviceDefinitionMap.put("com.ewcms.function.datasource.model.JdbcDS", "jdbcDataSourceFactory");
        serviceDefinitionMap.put("com.ewcms.function.datasource.model.JndiDS", "jndiDataSourceFactory");
        serviceDefinitionMap.put("com.ewcms.function.datasource.model.BeanDS", "beanDataSourceFactory");
        serviceDefinitionMap.put("com.ewcms.function.datasource.model.CustomDS", "customDataSourceFactory");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    	logger.info("EwcmsDataSourceServiceFactory Properties begin......");
        setBeanForInterfaceMappings(serviceDefinitionMap);
        logger.info("EwcmsDataSourceServiceFactory Properties end");
    }
    
    @SuppressWarnings("rawtypes")
	public Object getBean(Class itfClass) {
        return factory.getBean(getBeanForInterfaceMappings(), itfClass);
    }

    @SuppressWarnings("rawtypes")
    public String getBeanName(Class itfClass) {
        return factory.getBeanName(getBeanForInterfaceMappings(), itfClass);
    }

}
