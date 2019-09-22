package io.cjf.jinterviewback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.cjf.jinterviewback.dao")
public class JinterviewbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(JinterviewbackApplication.class, args);
    }

}
