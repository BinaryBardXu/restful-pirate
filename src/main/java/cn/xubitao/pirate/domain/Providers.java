package cn.xubitao.pirate.domain;

import cn.xubitao.pirate.persistence.provider.ProviderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class Providers {
    @Autowired
    private ProviderPersistence providerPersistence;

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    private List<Provider> providers;

    public int create(Provider provider) throws SQLException {
        return providerPersistence.create(provider);
    }

    public Provider findById(Integer id) throws SQLException {
        return providerPersistence.findById(id);
    }

    public Providers loadAll() throws SQLException {
        return providerPersistence.loadAll();
    }

    public int update(Provider provider, Integer id) throws SQLException {
        return providerPersistence.update(provider, id);
    }

    public int deleteById(Integer id) throws SQLException {
        return providerPersistence.deleteById(id);
    }
}
