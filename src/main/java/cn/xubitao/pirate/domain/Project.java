package cn.xubitao.pirate.domain;

import cn.xubitao.pirate.persistence.project.ProjectPersistence;
import cn.xubitao.pirate.persistence.project.ProjectTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class Project {
    @Autowired
    private ProjectPersistence projectPersistence;

    public int create(ProjectTable projectTable) throws SQLException {
        return projectPersistence.create(projectTable);
    }
}
