package cn.xubitao.pirate.domain.contract;

import cn.xubitao.dolphin.foundation.exceptions.ClientException;
import cn.xubitao.pirate.persistence.contract.ContractPersistence;
import cn.xubitao.pirate.persistence.contract.ContractsPersistence;
import com.google.common.collect.ImmutableMap;
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
    private ContractsPersistence contractsPersistence;
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
        Map conditions = ImmutableMap.of("deleteStatus", 0, "name", contract.getName(), "providerId", contract.getProvider().getId());
        List<Contract> ContractList = contractsPersistence.findByConditions(conditions);
        if (ContractList.size() > 0) {
            throw new ClientException("Contract已经存在,请核实你的数据.");
        }
        return contractsPersistence.create(contract);
    }

    public Contract findById(Integer id) throws SQLException {
        return contractsPersistence.findById(id);
    }

    public Contracts loadAll(Integer providerId) throws SQLException {
        Contracts contracts = contractsPersistence.loadAll(providerId);
        for (Contract contract : contracts.getContracts()) {
            contract.setContractPersistence(contractPersistence);
            contract.countRecords();
        }
        return contracts;
    }

    public Contract update(Contract contract, Integer id) throws Exception {
        Map conditions = ImmutableMap.of("deleteStatus", 0, "name", contract.getName());
        List<Contract> contracts = contractsPersistence.findByConditions(conditions);
        if (contracts.size() > 0 && contracts.get(0).getId() != id) {
            throw new ClientException("Contract已经存在,请核实你的数据.");
        }
        return contractsPersistence.update(contract, id);
    }

    public int deleteById(Integer id) throws SQLException {
        return contractsPersistence.deleteById(id);
    }

    public int deleteByProviderId(Integer providerId) throws SQLException {
        return contractsPersistence.deleteByProviderId(providerId);
    }

    public List<Contract> loadByConsumerKey(String consumerKey) throws SQLException {
        return contractsPersistence.loadByConsumerKey(consumerKey);
    }
}
