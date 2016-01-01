package cn.xubitao.pirate.persistence.provider;

import cn.xubitao.pirate.domain.Provider;
import cn.xubitao.pirate.domain.Providers;

import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
public interface ProviderPersistence {
    int create(Provider provider) throws SQLException;

    Provider findById(Integer id) throws SQLException;

    Providers loadAll() throws SQLException;

    int update(Provider provider, Integer id) throws SQLException;

    int deleteById(Integer id) throws SQLException;
}
