package com.smart.context;

import com.smart.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlApplicationContext {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/smart/context/beans.xml");
        Car car =ctx.getBean("car",Car.class);
    }
}
