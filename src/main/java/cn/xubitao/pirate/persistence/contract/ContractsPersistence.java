package cn.xubitao.pirate.persistence.contract;

import cn.xubitao.pirate.domain.contract.Contract;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xubitao on 12/25/15.
 */

@Repository
public interface ContractsPersistence {
    Integer create(Contract contract);

    Contract findById(@Param("id") Integer id);

    List<Contract> loadAll(@Param("providerId") Integer providerId);

    Integer update(Contract contract);

    Integer deleteById(@Param("id") Integer id);

    Boolean checkRedundancy(Contract contract);

    Integer deleteByProviderId(@Param("providerId") Integer providerId);

    List<Contract> findByConditions(Contract contract);

    List<Contract> loadByName(@Param("name") String name);
}
