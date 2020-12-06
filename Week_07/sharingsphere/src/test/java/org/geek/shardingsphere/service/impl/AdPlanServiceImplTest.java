package org.geek.shardingsphere.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.geek.shardingsphere.model.AdPlan;
import org.geek.shardingsphere.service.AdPlanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdPlanServiceImplTest {

    @Autowired
    private AdPlanService adPlanService;

    @Test
    public void getAdPlan() {
        for (int i = 0; i < 10; i++) {
            log.info("查询: {}", adPlanService.getAdPlan(4L));
        }
    }

    @Test
    public void addAdPlan() {
        for (int i = 0; i < 10; i++) {
            AdPlan adPlan = new AdPlan();
            adPlan.setUserId(1L);
            adPlan.setPlanName("name");
            adPlan.setPlanStatus(1);
            log.info("插入: {}", adPlanService.addAdPlan(adPlan));
        }
    }
}
