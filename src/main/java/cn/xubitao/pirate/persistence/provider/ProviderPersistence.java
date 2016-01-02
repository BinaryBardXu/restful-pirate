package cn.xubitao.pirate.persistence.provider;

import cn.xubitao.pirate.domain.Provider;
import cn.xubitao.pirate.domain.Providers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by xubitao on 12/25/15.
 */
public interface ProviderPersistence {
    Provider create(Provider provider) throws SQLException;

    Provider findById(Integer id) throws SQLException;

    Providers loadAll() throws SQLException;

    int update(Provider provider, Integer id) throws SQLException;

    int deleteById(Integer id) throws SQLException;

    List<Provider> findByFieldValues(Map fieldValues) throws SQLException;
}
