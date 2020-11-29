package org.geek.dynamic01.service.impl;

import org.geek.dynamic01.config.ReadOnly;
import org.geek.dynamic01.mapper.AdPlanMapper;
import org.geek.dynamic01.model.AdPlan;
import org.geek.dynamic01.service.AdPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdPlanServiceImpl implements AdPlanService {

    @Autowired
    private AdPlanMapper adPlanMapper;

    @ReadOnly
    @Override
    public AdPlan getAdPlan(Long id) {
        return adPlanMapper.getAdPlan(id);
    }

    @Override
    public AdPlan getAdPlan2(Long id) {
        return adPlanMapper.getAdPlan(id);
    }

}
