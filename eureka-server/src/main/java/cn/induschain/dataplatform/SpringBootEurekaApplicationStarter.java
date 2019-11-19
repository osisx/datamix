package cn.induschain.dataplatform;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringBootEurekaApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootEurekaApplicationStarter.class, args);
    }

}
