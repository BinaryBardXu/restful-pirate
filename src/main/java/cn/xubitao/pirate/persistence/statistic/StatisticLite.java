package cn.xubitao.pirate.persistence.statistic;

import cn.xubitao.dolphin.sqllite.Dolphin;
import cn.xubitao.pirate.domain.contract.Contract;
import cn.xubitao.pirate.domain.provider.Provider;
import cn.xubitao.pirate.domain.record.Record;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class StatisticLite implements StatisticPersistence {
    @Resource
    private Dolphin dolphin;
    private Dao<Record, Integer> recordDAO;
    private Dao<Contract, Integer> contractDAO;
    private Dao<Provider, Integer> providerDAO;

    public Dao<Provider, Integer> getProviderDAO() {
        if (providerDAO == null) {
            providerDAO = dolphin.lite(Provider.class);
        }
        return providerDAO;
    }

    public Dao<Contract, Integer> getContractDAO() {
        if (contractDAO == null) {
            contractDAO = dolphin.lite(Contract.class);
        }
        return contractDAO;
    }

    public Dao<Record, Integer> getRecordDAO() {
        if (recordDAO == null) {
            recordDAO = dolphin.lite(Record.class);
        }
        return recordDAO;
    }

    @Override
    public long countProviders() throws SQLException {
        QueryBuilder queryBuilder = getProviderDAO().queryBuilder();
        queryBuilder.where().eq("deleteStatus", 0);
        return queryBuilder.countOf();
    }

    @Override
    public long countContracts() throws SQLException {
        QueryBuilder queryBuilder = getContractDAO().queryBuilder();
        queryBuilder.where().eq("deleteStatus", 0);
        return queryBuilder.countOf();
    }

    @Override
    public long countRecords(Integer isHit) throws SQLException {
        QueryBuilder<Record, Integer> queryBuilder = getRecordDAO().queryBuilder();
        Where where = queryBuilder.where();
        where.eq("deleteStatus", 0);

        if (isHit != null) {
            where.and().eq("isHit", isHit);
        }
        queryBuilder.setWhere(where);
        return queryBuilder.countOf();
    }
}
