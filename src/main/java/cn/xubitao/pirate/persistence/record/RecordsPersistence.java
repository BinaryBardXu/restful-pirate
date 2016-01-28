package cn.xubitao.pirate.persistence.record;

import cn.xubitao.pirate.domain.record.Record;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by xubitao on 12/25/15.
 */
@Repository
public interface RecordsPersistence {
    Integer create(Record record);

    List<Record> loadAll(Map record);

    Record findById(@Param("id") Integer id);

}
