package cn.xubitao.pirate.domain;

import cn.xubitao.pirate.persistence.project.ProjectPersistence;
import cn.xubitao.pirate.persistence.project.ProjectTable;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRule;
import org.mockito.junit.MockitoRule;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by xubitao on 12/25/15.
 */
public class ProjectTest {

    @Rule
    public MockitoRule rule=MockitoJUnit.rule();
    @Mock
    private ProjectPersistence projectPersistence;

    @InjectMocks
    private Project project;

    @Test
    public void 添加一个新的项目() throws SQLException {
        String name = "foo";
        String version = "fee";
        ProjectTable projectTable = new ProjectTable();
        projectTable.setName(name);
        projectTable.setVersion(version);

        Integer one = new Integer(1);
        when(projectPersistence.create(projectTable)).thenReturn(one);
        Integer actualResult = project.create(projectTable);

        assertEquals(actualResult, one);
        verify(projectPersistence).create(projectTable);
    }
}