package com.epf.eventz.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.epf.eventz.service", "com.epf.eventz.dao",
        "com.epf.eventz.persistence" })
public class AppConfiguration {

}