package cn.xubitao.pirate.persistence.record;

import cn.xubitao.pirate.domain.record.Record;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by xubitao on 12/25/15.
 */
@Repository
public interface RecordsPersistence {
    Record create(Record record);

    List<Record> loadAll(Map record);
}
