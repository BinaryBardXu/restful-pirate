package cn.xubitao.pirate.persistence.provider;

import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
public interface ProviderPersistence {
    long countContract(Integer id) throws SQLException;
}
