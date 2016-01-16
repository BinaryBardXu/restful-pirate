package cn.xubitao.pirate.persistence.statistic;

import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
public interface StatisticPersistence {
    long countProviders() throws SQLException;

    long countContracts() throws SQLException;

    long countRecords(Integer isHit) throws SQLException;
}
