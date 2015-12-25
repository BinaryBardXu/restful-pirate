package cn.xubitao.pirate.persistence.project;

import cn.xubitao.dolphin.sqllite.Dolphin;
import com.j256.ormlite.dao.Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class ProjectLite implements ProjectPersistence {
    @Resource
    private Dolphin dolphin;

    public int create(ProjectTable projectTable) throws SQLException {
        Dao<ProjectTable, Integer> projectDAO = dolphin.lite(ProjectTable.class);
        return projectDAO.create(projectTable);
    }
}
