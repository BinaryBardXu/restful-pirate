package cn.xubitao.pirate.domain;

import cn.xubitao.pirate.persistence.project.ProjectPersistence;
import cn.xubitao.pirate.persistence.project.ProjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class Project {
    @Autowired
    private ProjectPersistence projectPersistence;

    public int create(ProjectModel projectModel) throws SQLException {
        return projectPersistence.create(projectModel);
    }
}
