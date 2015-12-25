package cn.xubitao.pirate.persistence.project;

import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
public interface ProjectPersistence {
    int create(ProjectTable projectTable) throws SQLException;
}
