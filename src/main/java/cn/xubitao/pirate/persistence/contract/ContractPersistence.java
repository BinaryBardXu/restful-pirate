package cn.xubitao.pirate.persistence.contract;

import java.sql.SQLException;

/**
 * Created by xubitao on 1/16/16.
 */
public interface ContractPersistence {
    long countRecords(Integer id) throws SQLException;
}
