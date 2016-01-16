package cn.xubitao.pirate.persistence.contract;

import cn.xubitao.dolphin.sqllite.Dolphin;
import cn.xubitao.pirate.domain.contract.Contract;
import cn.xubitao.pirate.domain.contract.Contracts;
import cn.xubitao.pirate.domain.provider.Provider;
import com.google.common.collect.ImmutableMap;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class ContractsLite implements ContractsPersistence {
    @Resource
    private Dolphin dolphin;
    private Dao<Contract, Integer> contractDAO;

    public Contract create(Contract contract) throws SQLException {
        getContractDAO().create(contract);
        return contract;
    }

    public Contract findById(Integer id) throws SQLException {
        Map conditions = ImmutableMap.of("id", id, "deleteStatus", 0);
        List<Contract> result = getContractDAO().queryForFieldValues(conditions);
        return result.size() > 0 ? result.get(0) : null;
    }

    public List<Contract> findByConditions(Map conditions) throws SQLException {
        return getContractDAO().queryForFieldValues(conditions);
    }

    public List<Contract> loadByConsumerKey(String consumerKey) throws SQLException {
        QueryBuilder<Contract, Integer> queryBuilder = getContractDAO().queryBuilder();
        queryBuilder.where().eq("deleteStatus", 0);
        QueryBuilder<Provider, Integer> providerQueryBuilder = getProviderDAO().queryBuilder();
        providerQueryBuilder.where().eq("consumerKey", consumerKey).and().eq("deleteStatus", 0);
        return queryBuilder.join(providerQueryBuilder).query();
    }

    public Contracts loadAll(Integer providerId) throws SQLException {
        QueryBuilder<Contract, Integer> queryBuilder = getContractDAO().queryBuilder();
        queryBuilder.where().eq("deleteStatus", 0).and().eq("providerId", providerId);
        Contracts Contracts = new Contracts();
        Contracts.setContracts(queryBuilder.orderBy("id", false).query());
        return Contracts;
    }

    public Contract update(Contract contract, Integer id) throws Exception {
        contract.setId(id);
        getContractDAO().update(contract);
        return findById(id);
    }

    public int deleteById(Integer id) throws SQLException {
        return getContractDAO().executeRawNoArgs("update Contract set deleteStatus=1 where id=" + id);
    }

    public int deleteByProviderId(Integer providerId) throws SQLException {
        return getContractDAO().executeRawNoArgs("update Contract set deleteStatus=1 where providerId=" + providerId);
    }

    public Dao<Contract, Integer> getContractDAO() {
        if (contractDAO == null) {
            contractDAO = dolphin.lite(Contract.class);
        }
        return contractDAO;
    }

    public Dao<Provider, Integer> getProviderDAO() {
        return dolphin.lite(Provider.class);
    }
}
