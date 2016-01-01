package cn.xubitao.pirate.persistence.provider;

import cn.xubitao.dolphin.sqllite.Dolphin;
import cn.xubitao.pirate.domain.ProviderEntity;
import com.j256.ormlite.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class ProviderLite implements ProviderPersistence {
    @Resource
    private Dolphin dolphin;

    public int create(ProviderEntity providerModel) throws SQLException {
        Dao<ProviderEntity, Integer> projectDAO = dolphin.lite(ProviderEntity.class);
        return projectDAO.create(providerModel);
    }

    public ProviderEntity findById(Integer id) throws SQLException {
        Dao<ProviderEntity, Integer> projectDAO = dolphin.lite(ProviderEntity.class);
        return projectDAO.queryForId(id);
    }
}
