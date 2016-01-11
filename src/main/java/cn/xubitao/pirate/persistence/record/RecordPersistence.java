package cn.xubitao.pirate.persistence.record;

import cn.xubitao.pirate.domain.record.Record;
import cn.xubitao.pirate.domain.record.Records;

import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
public interface RecordPersistence {
    Record create(Record record) throws SQLException;

    Records loadAll(Integer contractId) throws SQLException;
}
