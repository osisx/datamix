package com.induschain.workflow;


import com.induschain.starter.SpringBootStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootStarter.class)
public class WorkFlowServiceTest {

    @Autowired
    private WorkFlowFacade workFlowFacade;

    @Test
    public void deploy(){
        workFlowFacade.deploy("leave");
    }

    @Test
    public void startProcess(){

//        workFlowFacade.deploy();

        workFlowFacade.startProcess("leave");
    }

    @Test
    public void complete() {

        String instanceId = workFlowFacade.startProcess("leave");
        //审批不通过
        Map<String, Object> result = new HashMap<>();
        result.put("pass", "false");
        workFlowFacade.complete(instanceId,result);

        result = new HashMap<>();
        result.put("submit", "true");
        workFlowFacade.complete(instanceId,result);

    }


    @Test
    public void completePass(){

        String instanceId = "a3399485-0835-11ea-a9ef-be62bf819394";
        //审批不通过
        Map<String, Object> result = new HashMap<>();
        result.put("pass", "true");
        workFlowFacade.complete(instanceId,result);
    }

    @Test
    public void history(){
        workFlowFacade.history("98895602-078a-11ea-af9a-be62bf819394");
    }
}