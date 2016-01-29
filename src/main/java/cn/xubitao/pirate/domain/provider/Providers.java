package cn.xubitao.pirate.domain.provider;

import cn.xubitao.dolphin.foundation.exceptions.ClientException;
import cn.xubitao.pirate.domain.contract.Contracts;
import cn.xubitao.pirate.persistence.provider.ProviderPersistence;
import cn.xubitao.pirate.persistence.provider.ProvidersPersistence;
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
        checkRedundancy(provider);
        providersPersistence.create(provider);
        return providersPersistence.findById(provider.getId());
    }

    private void checkRedundancy(Provider provider) throws SQLException, ClientException {
        Boolean isRedundancy = providersPersistence.checkRedundancy(provider);
        if (isRedundancy) {
            throw new ClientException("The name '" + provider.getName() + "' is already exist.");
        }
    }

    public Provider findById(Integer id) throws SQLException {
        Provider provider = providersPersistence.findById(id);
        if (provider == null) {
            return null;
        }
        provider.setProviderPersistence(providerPersistence);
        provider.countContracts();
        return provider;
    }

    public Providers loadAll(String keyword) throws SQLException {
        Providers providers = new Providers();
        providers.setProviders(providersPersistence.loadAll(keyword));
        for (Provider provider : providers.getProviders()) {
            provider.setProviderPersistence(providerPersistence);
            provider.countContracts();
        }
        return providers;
    }

    public Provider update(Provider provider) throws SQLException, ClientException {
        checkRedundancy(provider);
        providersPersistence.update(provider);
        return providersPersistence.findById(provider.getId());
    }

    public int deleteById(Integer id) throws SQLException {
        int deleteResult = providersPersistence.deleteById(id);
        contracts.deleteByProviderId(id);
        return deleteResult;
    }
}
