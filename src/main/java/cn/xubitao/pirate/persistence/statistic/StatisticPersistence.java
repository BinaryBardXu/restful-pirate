package cn.xubitao.pirate.persistence.statistic;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by xubitao on 12/25/15.
 */
@Repository
public interface StatisticPersistence {
    Integer countProviders();

    Integer countContracts();

    Integer countRecords(@Param("isHit") Integer isHit);
}
