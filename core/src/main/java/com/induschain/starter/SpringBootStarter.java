package com.induschain.starter;

import com.induschain.workflow.WorkFlowFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @package: com.xkcoding.logback
 * @description: 启动类
 * @author: yongliang.j
 */
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages={"com.induschain"})
public class SpringBootStarter {

	public static void main(String[] args) {

        Long start = System.currentTimeMillis();

        ConfigurableApplicationContext context = SpringApplication.run(SpringBootStarter.class, args);

		log.info("spring boot启动耗时:{} ms",System.currentTimeMillis()-start);

	}
}
