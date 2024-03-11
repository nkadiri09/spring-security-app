package com.sec.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass=true)
public class SecuritybasicMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuritybasicMsApplication.class, args);
    }

}
