package cn.xubitao.pirate.persistence.provider;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by xubitao on 12/25/15.
 */
@Repository
public interface ProviderPersistence {
    Integer countContract(@Param("id") Integer id);
}
