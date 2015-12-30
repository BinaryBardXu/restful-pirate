package cn.xubitao.pirate.domain;

import cn.xubitao.pirate.persistence.provider.ProviderPersistence;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class ProviderEntities {
    @Autowired
    private ProviderPersistence providerPersistence;

    public int create(ProviderEntity providerEntity) throws SQLException {
        return providerPersistence.create(providerEntity);
    }

    public ProviderEntity findById(Integer id) throws SQLException {
        return providerPersistence.findById(id);
    }
}
