package cn.xubitao.pirate.domain.provider;

import cn.xubitao.dolphin.foundation.exceptions.ClientException;
import cn.xubitao.pirate.domain.contract.Contracts;
import cn.xubitao.pirate.persistence.provider.ProviderPersistence;
import cn.xubitao.pirate.persistence.provider.ProvidersPersistence;
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
    private ProvidersPersistence providersPersistence;
    @Autowired
    private ProviderPersistence providerPersistence;
    @Autowired
    private Contracts contracts;
    private List<Provider> providers;

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    public Provider create(final Provider provider) throws Exception {
        Map conditions = ImmutableMap.of("name", provider.getName(), "version", provider.getVersion());
        List<Provider> providerList = providersPersistence.findByConditions(conditions);
        if (providerList.size() > 0) {
            throw new ClientException("Provider已经存在,请核实你的数据.");
        }
        return providersPersistence.create(provider);
    }

    public Provider findById(Integer id) throws SQLException {
        Provider provider = providersPersistence.findById(id);
        provider.setProviderPersistence(providerPersistence);
        provider.countContracts();
        return provider;
    }

    public Providers loadAll(String keyword) throws SQLException {
        Providers providers = providersPersistence.loadAll(keyword);
        for (Provider provider : providers.getProviders()) {
            provider.setProviderPersistence(providerPersistence);
            provider.countContracts();
        }
        return providers;
    }

    public Provider update(Provider provider, Integer id) throws SQLException {
        providersPersistence.update(provider, id);
        return providersPersistence.findById(id);
    }

    public int deleteById(Integer id) throws SQLException {
        int deleteResult = providersPersistence.deleteById(id);
        contracts.deleteByProviderId(id);
        return deleteResult;
    }
}
