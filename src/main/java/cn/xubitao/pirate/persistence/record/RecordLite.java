package cn.xubitao.pirate.persistence.record;

import cn.xubitao.dolphin.sqllite.Dolphin;
import cn.xubitao.pirate.domain.record.Record;
import cn.xubitao.pirate.domain.record.Records;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class RecordLite implements RecordPersistence {
    public static final int NOT_EXIST = -1;
    @Resource
    private Dolphin dolphin;
    private Dao<Record, Integer> projectDAO;

    public Record create(Record record) throws SQLException {
        getProjectDAO().create(record);
        return record;
    }

    public Records loadAll(Integer contractId) throws SQLException {
        contractId = contractId == null ? NOT_EXIST : contractId;
        QueryBuilder<Record, Integer> queryBuilder = getProjectDAO().queryBuilder();
        queryBuilder.where().eq("contractId", contractId).and().eq("deleteStatus", 0);
        Records records = new Records();
        records.setRecords(queryBuilder.query());
        return records;
    }

    public Dao<Record, Integer> getProjectDAO() {
        if (projectDAO == null) {
            projectDAO = dolphin.lite(Record.class);
        }
        return projectDAO;
    }
}
