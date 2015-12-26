package cn.xubitao.pirate.persistence.provider;

import cn.xubitao.dolphin.sqllite.Dolphin;
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

    public int create(ProviderModel providerModel) throws SQLException {
        Dao<ProviderModel, Integer> projectDAO = dolphin.lite(ProviderModel.class);
        return projectDAO.create(providerModel);
    }
}
