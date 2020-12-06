package org.geek.shardingsphere.mapper;

import org.apache.ibatis.annotations.Param;
import org.geek.shardingsphere.model.AdPlan;
import org.springframework.stereotype.Repository;

@Repository
public interface AdPlanMapper {

    AdPlan getAdPlan(@Param("id") Long id);

    int addAdPlan(@Param("model") AdPlan adPlan);

}
