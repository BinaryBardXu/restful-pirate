package cn.xubitao.pirate.persistence.provider;

import cn.xubitao.dolphin.sqllite.Dolphin;
import cn.xubitao.pirate.domain.contract.Contract;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class ProviderLite implements ProviderPersistence {
    @Resource
    private Dolphin dolphin;
    private Dao<Contract, Integer> contractDAO;

    public Dao<Contract, Integer> getContractDAO() {
        if (contractDAO == null) {
            contractDAO = dolphin.lite(Contract.class);
        }
        return contractDAO;
    }

    public long countContract(Integer id) throws SQLException {
        QueryBuilder queryBuilder = getContractDAO().queryBuilder();
        return queryBuilder.where().eq("providerId", id).and().eq("deleteStatus", 0).countOf();
    }
}
