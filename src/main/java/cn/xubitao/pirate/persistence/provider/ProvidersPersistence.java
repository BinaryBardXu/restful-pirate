package cn.xubitao.pirate.persistence.provider;

import cn.xubitao.pirate.domain.provider.Provider;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xubitao on 12/25/15.
 */
@Repository
public interface ProvidersPersistence {
    Integer create(Provider provider);

    Provider findById(@Param("id") Integer id);

    List<Provider> loadAll(@Param("keyword") String keyword);

    Integer update(Provider provider);

    Integer deleteById(@Param("id") Integer id);

    List<Provider> findByConditions(Provider fieldValues);
}
