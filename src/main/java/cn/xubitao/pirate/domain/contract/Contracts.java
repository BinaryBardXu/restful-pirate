package cn.xubitao.pirate.domain.contract;

import cn.xubitao.dolphin.foundation.exceptions.ClientException;
import cn.xubitao.pirate.persistence.contract.ContractPersistence;
import com.google.common.collect.ImmutableMap;
import com.j256.ormlite.stmt.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class Contracts {
    @Autowired
    private ContractPersistence contractPersistence;

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    private List<Contract> contracts;

    public Contract create(final Contract contract) throws Exception {
        Map conditions = ImmutableMap.of("deleteStatus", 0, "name", contract.getName(), "providerId", contract.getProviderId());
        List<Contract> ContractList = contractPersistence.findByConditions(conditions);
        if (ContractList.size() > 0) {
            throw new ClientException("Contract已经存在,请核实你的数据.");
        }
        return contractPersistence.create(contract);
    }

    public Contract findById(Integer id) throws SQLException {
        return contractPersistence.findById(id);
    }

    public Contracts loadAll(Integer providerId) throws SQLException {
        return contractPersistence.loadAll(providerId);
    }

    public Contract update(Contract contract, Integer id) throws Exception {
        Map conditions = ImmutableMap.of("deleteStatus", 0, "name", contract.getName());
        List<Contract> contracts = contractPersistence.findByConditions(conditions);
        if (contracts.size() > 0 && contracts.get(0).getId() != id) {
            throw new ClientException("Contract已经存在,请核实你的数据.");
        }
        return contractPersistence.update(contract, id);
    }

    public int deleteById(Integer id) throws SQLException {
        return contractPersistence.deleteById(id);
    }

    public int deleteByProviderId(Integer providerId) throws SQLException {
        return contractPersistence.deleteByProviderId(providerId);
    }

    public List<Contract> loadByConsumerKey(String consumerKey) throws SQLException {
        return contractPersistence.loadByConsumerKey(consumerKey);
    }
}
