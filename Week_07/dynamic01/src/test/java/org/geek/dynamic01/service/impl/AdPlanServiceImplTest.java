package org.geek.dynamic01.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.geek.dynamic01.service.AdPlanService;
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
        log.info("从：{}", adPlanService.getAdPlan(1L));
    }

    @Test
    public void getAdPlan2() {
        log.info("主：{}", adPlanService.getAdPlan2(1L));
    }
}
