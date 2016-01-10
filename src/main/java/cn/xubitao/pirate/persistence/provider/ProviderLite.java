package cn.xubitao.pirate.persistence.provider;

import cn.xubitao.dolphin.sqllite.Dolphin;
import cn.xubitao.pirate.domain.provider.Provider;
import cn.xubitao.pirate.domain.provider.Providers;
import com.google.common.collect.ImmutableMap;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
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
        Map conditions = ImmutableMap.of("id", id, "deleteStatus", 0);
        List<Provider> result = getProjectDAO().queryForFieldValues(conditions);
        return result.size() > 0 ? result.get(0) : null;
    }

    public List<Provider> findByConditions(Map conditions) throws SQLException {
        return getProjectDAO().queryForFieldValues(conditions);
    }

    public Providers loadAll(String keyword) throws SQLException {
        keyword = keyword == null ? "%%" : "%" + keyword + "%";
        QueryBuilder<Provider, Integer> queryBuilder = getProjectDAO().queryBuilder();
        queryBuilder.where().like("name", keyword).or().like("consumerKey", keyword).and().eq("deleteStatus", 0);

        Providers providers = new Providers();
        providers.setProviders(queryBuilder.query());
        return providers;
    }

    public int update(Provider provider, Integer id) throws SQLException {
        provider.setId(id);
        return getProjectDAO().update(provider);
    }

    public int deleteById(Integer id) throws SQLException {
        return getProjectDAO().executeRawNoArgs("update provider set deleteStatus=1 where id=" + id);
    }

    public Dao<Provider, Integer> getProjectDAO() {
        if (projectDAO == null) {
            projectDAO = dolphin.lite(Provider.class);
        }
        return projectDAO;
    }
}
