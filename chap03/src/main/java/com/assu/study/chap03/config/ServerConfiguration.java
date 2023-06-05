package com.assu.study.chap03.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@ComponentScan(basePackages = {"com.assu.study.chap03.config", "com.assu.study.chap03.domain"},
//basePackageClasses = {ThreadPoolConfig.class})
@Import(value = {ThreadPoolConfig.class, ServerConfig.class})
public class ServerConfiguration {
    // TODO ...
}
