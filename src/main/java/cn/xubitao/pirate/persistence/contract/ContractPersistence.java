package cn.xubitao.pirate.persistence.contract;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by xubitao on 1/16/16.
 */
@Repository
public interface ContractPersistence {
    Integer countRecords(@Param("id") Integer id);
}
