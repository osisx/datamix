package com.induschain.workflow;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WorkFlowFacade {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private HistoryService historyService;

    /**
     * 部署流程
     */
    public void deploy(String deployKey){
        //创建一个部署对象
        Deployment deployment = repositoryService.createDeployment()
                .name(BusinessProcessEnum.getName(deployKey)).key(deployKey)
                .addClasspathResource(WorkFlowConstant.PROCESS_PATH + deployKey + WorkFlowConstant.BPMN)
                .deploy();

        log.info("流程部署成功,部署ID:{},部署名称:{},部署KEY:{}", deployment.getId(), deployment.getName(), deployment.getKey());
    }

    /**
     * 启动流程
     */
    public String startProcess(String instanceKey){

        Deployment deployment = repositoryService.createDeploymentQuery().deploymentKey(instanceKey).singleResult();
        if (deployment == null) {
            this.deploy(instanceKey);
        }

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(instanceKey);
        log.info("InstanceId:{},InstanceKey:{}", processInstance.getId(), processInstance.getProcessDefinitionKey());

        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        log.info("InstanceId:{},当前结点:{},处理人:{},任务ID:{}", processInstance.getId(), task.getName(), task.getAssignee());

        return processInstance.getId();
    }


    /**
     * 审批
     * @param instanceId
     * @param result
     */
    public void complete(String instanceId, Map<String, Object> result) {

        Task task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        if (task == null) {
            log.info("instanceId:{},流程结束", instanceId);
        } else {
            log.info("InstanceId:{},当前结点:{},处理人:{},任务ID:{}", instanceId, task.getName(), task.getAssignee(),task.getId());
        }

        //处理当前任务
        taskService.complete(task.getId(), result, true);

        task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        if (task == null) {
            log.info("InstanceId:{},流程结束", instanceId);
        } else {
            log.info("instanceId:{},下一步结点:{},处理人:{},任务ID:{}", instanceId, task.getName(), task.getAssignee(),task.getId());
        }
    }


    public void history(String instanceId){
        List<HistoricActivityInstance> hais = processEngine.getHistoryService()//
                .createHistoricActivityInstanceQuery()
                .processInstanceId(instanceId)
                .list();

        for (HistoricActivityInstance his:hais){
            log.info("审批人:{},结点:{}",his.getAssignee(),his.getActivityName());
        }
    }

}
