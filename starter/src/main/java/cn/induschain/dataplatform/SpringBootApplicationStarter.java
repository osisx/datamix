package cn.induschain.dataplatform;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

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
public class SpringBootApplicationStarter {

	public static void main(String[] args) {

        Long start = System.currentTimeMillis();

        ConfigurableApplicationContext context = SpringApplication.run(SpringBootApplicationStarter.class, args);

		log.info("spring boot启动耗时:{} ms",System.currentTimeMillis()-start);

		RepositoryService repositoryService = context.getBeanFactory().getBean(RepositoryService.class);

//		Deployment deployment = repositoryService.createDeployment()//创建一个部署对象
//				.name("请假流程")
//				.addClasspathResource("processes/team.bpmn")
//				.deploy();
//		System.out.println("部署ID："+deployment.getId());
//		System.out.println("部署名称："+deployment.getName());

		DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
		// 使用repositoryService查询多个部署对象
		List<Deployment> deploymentList = deploymentQuery
				.orderByDeploymenTime()
				.asc()
				.listPage(0,100);

		log.info("流程数量：{}",deploymentList.size());
	}
}
