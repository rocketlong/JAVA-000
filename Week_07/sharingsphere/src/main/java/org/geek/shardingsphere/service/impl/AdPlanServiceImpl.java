package org.geek.shardingsphere.service.impl;

import org.geek.shardingsphere.mapper.AdPlanMapper;
import org.geek.shardingsphere.model.AdPlan;
import org.geek.shardingsphere.service.AdPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdPlanServiceImpl implements AdPlanService {

    @Autowired
    private AdPlanMapper adPlanMapper;

    @Override
    public AdPlan getAdPlan(Long id) {
        return adPlanMapper.getAdPlan(id);
    }

    @Override
    public int addAdPlan(AdPlan adPlan) {
        return adPlanMapper.addAdPlan(adPlan);
    }

}
