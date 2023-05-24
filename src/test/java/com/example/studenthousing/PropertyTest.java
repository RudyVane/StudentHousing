package com.example.studenthousing;

import com.example.studenthousing.services.PropertyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PropertyTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("com.example.studenthousing");
        appContext.refresh();

        PropertyService PropertyService = (PropertyService) appContext.getBean("propertyService");
        PropertyService.test();

        appContext.close();
    }

}
