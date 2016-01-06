package cn.xubitao.pirate.persistence.contract;

import cn.xubitao.pirate.domain.Contract;
import cn.xubitao.pirate.domain.Contracts;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by xubitao on 12/25/15.
 */
public interface ContractPersistence {
    Contract create(Contract contract) throws SQLException;

    Contract findById(Integer id) throws SQLException;

    Contracts loadAll(Integer providerId) throws SQLException;

    int update(Contract Contract, Integer id) throws SQLException;

    int deleteById(Integer id) throws SQLException;

    List<Contract> findByConditions(Map fieldValues) throws SQLException;
}
