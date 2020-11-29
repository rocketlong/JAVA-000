package org.geek.dynamic01.mapper;

import org.apache.ibatis.annotations.Param;
import org.geek.dynamic01.model.AdPlan;
import org.springframework.stereotype.Repository;

@Repository
public interface AdPlanMapper {

    AdPlan getAdPlan(@Param("id") Long id);

}
