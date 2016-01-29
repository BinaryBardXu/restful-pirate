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
        checkRedundancy(contract);
        contractsPersistence.create(contract);
        return contractsPersistence.findById(contract.getId());
    }

    private void checkRedundancy(Contract contract) throws ClientException {
        Boolean isRedundancy = contractsPersistence.checkRedundancy(contract);
        if (isRedundancy) {
            throw new ClientException("The name '" + contract.getName() + "' is already exist.");
        }
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
        checkRedundancy(contract);
        contractsPersistence.update(contract);
        return contractsPersistence.findById(contract.getId());
    }

    public int deleteById(Integer id) throws SQLException {
        return contractsPersistence.deleteById(id);
    }

    public int deleteByProviderId(Integer providerId) throws SQLException {
        return contractsPersistence.deleteByProviderId(providerId);
    }

    public List<Contract> loadByName(String name) throws SQLException {
        return contractsPersistence.loadByName(name);
    }
}
