package cn.xubitao.pirate.domain.provider;

import cn.xubitao.dolphin.foundation.exceptions.ClientException;
import cn.xubitao.pirate.persistence.provider.ProviderPersistence;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    public Provider create(final Provider provider) throws Exception {
        Map conditions = ImmutableMap.of("name", provider.getName(), "version", provider.getVersion());
        List<Provider> providerList = providerPersistence.findByConditions(conditions);
        if (providerList.size() > 0) {
            throw new ClientException("Provider已经存在,请核实你的数据.");
        }
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
