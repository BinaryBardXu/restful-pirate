package cn.xubitao.pirate.domain.contract;

import cn.xubitao.dolphin.foundation.exceptions.ClientException;
import cn.xubitao.pirate.persistence.contract.ContractPersistence;
import cn.xubitao.pirate.persistence.contract.ContractsPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class Contracts {
    @Autowired
    private ContractsPersistence contractsPersistence;
    @Autowired
    private ContractPersistence contractPersistence;
    private List<Contract> contracts;

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public Contract create(final Contract contract) throws Exception {
        List<Contract> ContractList = contractsPersistence.findByConditions(contract);
        if (ContractList.size() > 0) {
            throw new ClientException("Contract已经存在,请核实你的数据.");
        }
        contractsPersistence.create(contract);
        return contractsPersistence.findById(contract.getId());
    }

    public Contract findById(Integer id) throws SQLException {
        return contractsPersistence.findById(id);
    }

    public Contracts loadAll(Integer providerId) throws SQLException {
        Contracts contracts = new Contracts();
        contracts.setContracts(contractsPersistence.loadAll(providerId));
        for (Contract contract : contracts.getContracts()) {
            contract.setContractPersistence(contractPersistence);
            contract.countRecords();
        }
        return contracts;
    }

    public Contract update(Contract contract) throws Exception {
        Boolean isRedundancy = contractsPersistence.checkRedundancy(contract);
        if (isRedundancy) {
            throw new ClientException("Contract已经存在,请核实你的数据.");
        }
        contractsPersistence.update(contract);
        return contractsPersistence.findById(contract.getId());
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
