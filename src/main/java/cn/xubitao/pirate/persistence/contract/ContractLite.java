package cn.xubitao.pirate.persistence.contract;

import cn.xubitao.dolphin.sqllite.Dolphin;
import cn.xubitao.pirate.domain.record.Record;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class ContractLite implements ContractPersistence {
    @Resource
    private Dolphin dolphin;
    private Dao<Record, Integer> recordDAO;

    public Dao<Record, Integer> getRecordDAO() {
        if (recordDAO == null) {
            recordDAO = dolphin.lite(Record.class);
        }
        return recordDAO;
    }

    public long countRecords(Integer id) throws SQLException {
        QueryBuilder queryBuilder = getRecordDAO().queryBuilder();
        return queryBuilder.where().eq("contractId", id).and().eq("deleteStatus", 0).countOf();

    }
}
