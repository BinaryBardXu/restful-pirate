package cn.xubitao.pirate.domain;

import cn.xubitao.dolphin.foundation.exceptions.ClientException;
import cn.xubitao.pirate.persistence.contract.ContractPersistence;
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
    private ContractPersistence contractPersistence;

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    private List<Contract> contracts;

    public Contract create(final Contract Contract) throws Exception {
        Map conditions = ImmutableMap.of("name", Contract.getName());
        List<Contract> ContractList = contractPersistence.findByConditions(conditions);
        if (ContractList.size() > 0) {
            throw new ClientException("Contract已经存在,请核实你的数据.");
        }
        return contractPersistence.create(Contract);
    }

    public Contract findById(Integer id) throws SQLException {
        return contractPersistence.findById(id);
    }

    public Contracts loadAll(Integer providerId) throws SQLException {
        return contractPersistence.loadAll(providerId);
    }

    public int update(Contract Contract, Integer id) throws SQLException {
        return contractPersistence.update(Contract, id);
    }

    public int deleteById(Integer id) throws SQLException {
        return contractPersistence.deleteById(id);
    }
}
