package cn.xubitao.pirate.persistence.provider;

import cn.xubitao.dolphin.sqllite.Dolphin;
import cn.xubitao.pirate.domain.Provider;
import cn.xubitao.pirate.domain.Providers;
import com.j256.ormlite.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class ProviderLite implements ProviderPersistence {
    @Resource
    private Dolphin dolphin;
    private Dao<Provider, Integer> projectDAO;

    public Provider create(Provider provider) throws SQLException {
        getProjectDAO().create(provider);
        return provider;
    }

    public Provider findById(Integer id) throws SQLException {
        return getProjectDAO().queryForId(id);
    }

    public List<Provider> findByFieldValues(Map fieldValues) throws SQLException {
        return getProjectDAO().queryForFieldValues(fieldValues);
    }

    public Providers loadAll() throws SQLException {
        Providers providers = new Providers();
        providers.setProviders(getProjectDAO().queryForAll());
        return providers;
    }

    public int update(Provider provider, Integer id) throws SQLException {
        provider.setId(id);
        return getProjectDAO().update(provider);
    }

    public int deleteById(Integer id) throws SQLException {
        return getProjectDAO().deleteById(id);
    }

    public Dao<Provider, Integer> getProjectDAO() {
        if (projectDAO == null) {
            projectDAO = dolphin.lite(Provider.class);
        }
        return projectDAO;
    }
}
