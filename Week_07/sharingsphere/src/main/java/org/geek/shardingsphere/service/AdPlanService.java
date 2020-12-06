package org.geek.shardingsphere.service;

import org.geek.shardingsphere.model.AdPlan;

public interface AdPlanService {

    AdPlan getAdPlan(Long id);

    int addAdPlan(AdPlan adPlan);

}
