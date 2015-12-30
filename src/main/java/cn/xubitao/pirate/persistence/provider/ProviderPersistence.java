package cn.xubitao.pirate.persistence.provider;

import cn.xubitao.pirate.domain.ProviderEntity;

import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
public interface ProviderPersistence {
    int create(ProviderEntity providerEntity) throws SQLException;

    ProviderEntity findById(Integer id) throws SQLException;
}
