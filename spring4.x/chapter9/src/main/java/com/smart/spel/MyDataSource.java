package com.smart.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyDataSource {

    @Value("#{properties['driverClassName']}")
	private String driverClassName;
    
    @Value("${url}") // 注意bean.xml定义了： property-placeholder = "properties"，所以可以简写
	private String url;
    
    @Value("#{properties['userName']}")
	private String userName;
    
    @Value("#{properties['password']}")
	private String password;

}
