package cn.xubitao.pirate.domain;

import cn.xubitao.pirate.persistence.provider.ProviderPersistence;
import cn.xubitao.pirate.persistence.provider.ProviderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class Provider {
    @Autowired
    private ProviderPersistence providerPersistence;

    public int create(ProviderModel providerModel) throws SQLException {
        return providerPersistence.create(providerModel);
    }

    public ProviderModel findById(Integer id) throws SQLException {
        return providerPersistence.findById(id);
    }
}
