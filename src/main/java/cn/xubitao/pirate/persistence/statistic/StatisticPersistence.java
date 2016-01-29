package cn.xubitao.pirate.persistence.statistic;

import cn.xubitao.pirate.domain.record.Record;
import org.springframework.stereotype.Repository;

/**
 * Created by xubitao on 12/25/15.
 */
@Repository
public interface StatisticPersistence {
    Integer countProviders();

    Integer countContracts();

    Integer countRecords(Record record);
}
