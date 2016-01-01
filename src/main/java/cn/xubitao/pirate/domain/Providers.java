package cn.xubitao.pirate.domain;

import cn.xubitao.pirate.persistence.provider.ProviderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class Providers {
    @Autowired
    private ProviderPersistence providerPersistence;

    public int create(Provider provider) throws SQLException {
        return providerPersistence.create(provider);
    }

    public Provider findById(Integer id) throws SQLException {
        return providerPersistence.findById(id);
    }
}
